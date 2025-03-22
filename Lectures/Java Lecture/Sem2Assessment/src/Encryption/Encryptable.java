package Encryption;

public interface Encryptable {
	String encrypt(String data);
    String decrypt(String encryptedData);
}
