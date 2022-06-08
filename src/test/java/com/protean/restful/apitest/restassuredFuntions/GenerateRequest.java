package com.protean.restful.apitest.restassuredFuntions;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONObject;

import com.protean.restful.apitest.actions.ProjectConfig;
import com.protean.restful.apitest.listeners.ExtentTestManager;
import com.protean.restful.apitest.listeners.TestData;
import com.protean.restful.apitest.testcases.Hooks;
import com.relevantcodes.extentreports.LogStatus;

public class GenerateRequest {

	static String req;
	public static String request = null;
	public static String headers = null;
	static String authHeader = null;
	// Domain: nic2004:52110 - Retail, Domain: nic2004:60232 - Logistics

	// Retail search
	// static String kid = "mock_bpp1|bpp_one|ed25519"; //Retail on_search

	// static String kid = "nsdl.co.in.ba|145|ed25519"; //Logistics search
	// static String kid = "nsdl.co.in.bp|179|ed25519"; //Logistics on_search

	public static void getRequest(String scenario, String searchType) {
		setup();
		try {
			StringBuilder sb = new StringBuilder();
			UUID uuid = UUID.randomUUID();
			String generatedString = uuid.toString();
			
			//ExtentTestManager.log(LogStatus.INFO, "Your UUID is: " + generatedString);
			switch (searchType) {
			case "Retail_Search":
				req = "{\"context\":{\"domain\":\"nic2004:52110\",\"country\":\"IND\",\"city\":\"std:080\",\"action\":\"search\",\"core_version\":\"0.9.1\",\"bap_id\":\"mock_bap1\",\"bap_uri\":\"http://localhost:8089/\",\"transaction_id\":\""
						+ generatedString
						+ "\",\"message_id\":\"153922db-69f7-4418-b7ce-1c6f28f9d9c4\",\"timestamp\":\"2022-03-07T07:05:37.976711Z\"},\"message\":{\"intent\":{\"fulfillment\":{\"start\":{\"location\":{}},\"end\":{\"location\":{\"gps\":\"19.1178548,72.8631304\"}}},\"item\":{\"descriptor\":{\"name\":\"printer\"}},\"provider\":{\"descriptor\":{}},\"category\":{\"descriptor\":{}}}}}";
				break;
			case "Retail_On_Search":
				req = "{\"context\":{\"domain\":\"nic2004:52110\",\"country\":\"IND\",\"city\":\"std:080\",\"action\":\"search\",\"core_version\":\"0.9.1\",\"bap_id\":\"mock_bap1\",\"bap_uri\":\"http://localhost:8089/\",\"transaction_id\":\""
						+ generatedString
						+ "\",\"message_id\":\"153922db-69f7-4418-b7ce-1c6f28f9d9c4\",\"timestamp\":\"2022-03-07T07:05:37.976711Z\"},\"message\":{\"intent\":{\"fulfillment\":{\"start\":{\"location\":{}},\"end\":{\"location\":{\"gps\":\"19.1178548,72.8631304\"}}},\"item\":{\"descriptor\":{\"name\":\"printer\"}},\"provider\":{\"descriptor\":{}},\"category\":{\"descriptor\":{}}}}}";
				break;
			case "Logistics_Search":
				req = "{\"context\":{\"domain\":\"nic2004:52110\",\"country\":\"IND\",\"city\":\"std:080\",\"action\":\"search\",\"core_version\":\"0.9.1\",\"bap_id\":\"mock_bap1\",\"bap_uri\":\"http://localhost:8089/\",\"transaction_id\":\""
						+ generatedString
						+ "\",\"message_id\":\"153922db-69f7-4418-b7ce-1c6f28f9d9c4\",\"timestamp\":\"2022-03-07T07:05:37.976711Z\"},\"message\":{\"intent\":{\"fulfillment\":{\"start\":{\"location\":{}},\"end\":{\"location\":{\"gps\":\"19.1178548,72.8631304\"}}},\"item\":{\"descriptor\":{\"name\":\"printer\"}},\"provider\":{\"descriptor\":{}},\"category\":{\"descriptor\":{}}}}}";
				break;
			case "Logistics_On_Search":
				req = "{\"context\":{\"domain\":\"nic2004:52110\",\"country\":\"IND\",\"city\":\"std:080\",\"action\":\"search\",\"core_version\":\"0.9.1\",\"bap_id\":\"mock_bap1\",\"bap_uri\":\"http://localhost:8089/\",\"transaction_id\":\""
						+ generatedString
						+ "\",\"message_id\":\"153922db-69f7-4418-b7ce-1c6f28f9d9c4\",\"timestamp\":\"2022-03-07T07:05:37.976711Z\"},\"message\":{\"intent\":{\"fulfillment\":{\"start\":{\"location\":{}},\"end\":{\"location\":{\"gps\":\"19.1178548,72.8631304\"}}},\"item\":{\"descriptor\":{\"name\":\"printer\"}},\"provider\":{\"descriptor\":{}},\"category\":{\"descriptor\":{}}}}}";
				break;
			}

			long testTimestamp = System.currentTimeMillis() / 1000L;

			sb.append(req);
			sb.append("^");
			/*
			 * ExtentTestManager.log(LogStatus.INFO, "Test Timestamp :" + testTimestamp);
			 * 
			 * ExtentTestManager.log(LogStatus.INFO,
			 * "==============================Json Request==================================="
			 * ); ExtentTestManager.log(LogStatus.INFO, req);
			 */
			Hooks.scenario.log("Request is -----"+req);
			request = req;
			String blakeValue;
			try {
				blakeValue = generateBlakeHash(req);

				/*
				 * ExtentTestManager.log(LogStatus.INFO,
				 * "==============================Digest Value ==================================="
				 * );
				 */
				String signingString = "(created): " + 1651483719 + "\n(expires): " + (testTimestamp + 60000)
						+ "\ndigest: BLAKE-512=" + blakeValue + "";

//				ExtentTestManager.log(LogStatus.INFO,
//						"==============================Data to Sign===================================");
//				ExtentTestManager.log(LogStatus.INFO, signingString);

				String header = "(" + 1651483719 + ") (" + (testTimestamp + 60000) + ") BLAKE-512=" + blakeValue + "";

				String signedReq = generateSignature(signingString, ProjectConfig.getPropertyValue("privateKey"));

				//ExtentTestManager.log(LogStatus.INFO, "Signature : " + signedReq);

				switch (searchType) {
				case "Retail_Search":
					authHeader = "Signature keyId=\"" + ProjectConfig.getPropertyValue("Retail_Search_kid")
							+ "\",algorithm=\"ed25519\", created=\"" + 1651483719 + "\", expires=\""
							+ (testTimestamp + 60000) + "\", headers=\"(created) (expires) digest\", signature=\""
							+ signedReq + "\"";
					break;

				case "Retail_On_Search":
					authHeader = "Signature keyId=\"" + ProjectConfig.getPropertyValue("Retail_On_Search_kid")
							+ "\",algorithm=\"ed25519\", created=\"" + 1651483719 + "\", expires=\""
							+ (testTimestamp + 60000) + "\", headers=\"(created) (expires) digest\", signature=\""
							+ signedReq + "\"";
					break;
				case "Logistics_Search":
					break;

				case "Logistics_On_Search":
					break;
				default:
					break;
				}

//				ExtentTestManager.log(LogStatus.INFO,
//						"==============================Signed Request=================================");
//				ExtentTestManager.log(LogStatus.INFO, authHeader);
				switch (scenario) {
				case "SearchRequestWithoutheader":
					headers = null;
					break;
				case "SearchRequestForIncorrectSignature":
					headers = authHeader + " incorrect Signature";
					break;
				case "SearchRequestForIncorrectHeader":
					headers = authHeader + " Incorrect Header";
					break;
				default:
					headers = authHeader;
					break;
				}
				sb.append(authHeader);
				sb.append("\n");

				// To Verify Signature
//				ExtentTestManager.log(LogStatus.INFO,
//						"==============================Verify Signature================================");

				// signedReq =
				// "8cZbtVdtBwIlEdYyEp7bMf31I5J+toJpTmvQKyiWkbnLjKLduIcsnbzu7dlURHuXHGnuQ+pgc2GBmi2ZG49QBw";
				// signingString = "(created): 1607074370\n(expires): 1607160770\ndigest:
				// BLAKE-512=w1C1nPKuym9rOszLnqSWjnXTCmmo4i9JcIeUJGiESd2rJHUFAMGQ0wEAlVGh4C5DTjH7y4poIss5kDehariaJw==";
				// String pk = "kCa4OlmRVfCPcvzjPPGik0Ljei5dRYuuj/2K6upaf1E=";

				verifySignature(signedReq, signingString, ProjectConfig.getPropertyValue("publicKey"));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String generateSignature(String req, String pk) {
		String signature = null;
		try {
			Ed25519PrivateKeyParameters privateKey = new Ed25519PrivateKeyParameters(
					Base64.getDecoder().decode(pk.getBytes()), 0);
			Signer sig = new Ed25519Signer();
			sig.init(true, privateKey);
			sig.update(req.getBytes(), 0, req.length());
			byte[] s1 = sig.generateSignature();
			signature = Base64.getEncoder().encodeToString(s1);
		} catch (DataLengthException | CryptoException e) {
			e.printStackTrace();
		}
		return signature;
	}

	/*
	 * public static String generateBlakeHash(String req) { Blake2bDigest
	 * blake2bDigest = new Blake2bDigest(512); byte[] test = req.getBytes();
	 * blake2bDigest.update(test, 0, test.length); byte[] hash = new
	 * byte[blake2bDigest.getDigestSize()]; blake2bDigest.doFinal(hash, 0); //return
	 * Base64.getEncoder().encodeToString(hash);
	 * 
	 * //String hex = Hex.toHexString(hash); //System.out.println("Hex : " + hex);
	 * //System.out.println("returning hash: " +
	 * Base64.getEncoder().encodeToString(hash)); String bs64 =
	 * Base64.getUrlEncoder().encodeToString(hash);
	 * System.out.println("Base64 URL Encoded : " + bs64); return bs64; }
	 */

	public static void setup() {
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
			System.out.println(Security.addProvider(new BouncyCastleProvider()));
		}
	}

	public static String generateBlakeHash(String req) throws Exception {
		// String stringToHash = "Beckn!";

		MessageDigest digest = MessageDigest.getInstance("BLAKE2B-512", BouncyCastleProvider.PROVIDER_NAME);
		digest.reset();
		digest.update(req.getBytes(StandardCharsets.UTF_8));
		byte[] hash = digest.digest();
		String bs64 = Base64.getEncoder().encodeToString(hash);
		//ExtentTestManager.log(LogStatus.INFO, bs64);
		return bs64;

		// System.out.println(toHex(hash)); // We could use bs64 or hex of the hash (Not
		// sure why we need to hex and then base64 it!! Seems overkill.
		// https://8gwifi.org/MessageDigest.jsp

	}

	public static boolean verifySignature(String sign, String requestData, String dbPublicKey) {
		boolean isVerified = false;
		try {
//			ExtentTestManager.log(LogStatus.INFO,
//					"Sign : " + sign + " requestData : " + requestData + " PublicKey : " + dbPublicKey);
			// Ed25519PublicKeyParameters publicKey = new
			// Ed25519PublicKeyParameters(Hex.decode(dbPublicKey), 0);
			Ed25519PublicKeyParameters publicKey = new Ed25519PublicKeyParameters(
					Base64.getDecoder().decode(dbPublicKey), 0);
			Signer sv = new Ed25519Signer();
			sv.init(false, publicKey);
			sv.update(requestData.getBytes(), 0, requestData.length());

			byte[] decodedSign = Base64.getDecoder().decode(sign);
			isVerified = sv.verifySignature(decodedSign);
			// ExtentTestManager.log(LogStatus.INFO, "Is Sign Verified : " + isVerified);
			// System.out.println("Is Sign Verified : " + isVerified);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isVerified;
	}
}
