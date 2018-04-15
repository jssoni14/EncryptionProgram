import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;


public class encrypt {
	 
	
	private static int len = 2048;
	public static void main(String[] args) {
	System.out.println("Generating very large prime numbers...\n");
	
	/*Generate prime numbers p and q and set the value for e*/
	
	SecureRandom r = new SecureRandom();
	BigInteger p = BigInteger.probablePrime(len,r);
	BigInteger q = BigInteger.probablePrime(len,r);
	BigInteger e = new BigInteger("65537");
	
	/*Calculate phi and initialize public key*/
	
	BigInteger phi =  p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
	BigInteger publickey = p.multiply(q);
	
	/*Check if p and qs difference is greater then 1000*/
	
	BigInteger abs = p.subtract(q);
	abs = abs.abs();
	BigInteger abs2 = q.subtract(p);
	abs2 = abs2.abs();
	
	/*Initialize 2^1000 for comparison*/
	
	BigInteger compare = new BigInteger("10715086071862673209484250490600018105614048117055336074437503883703510511249361224931983788156958581275946729175531468251871452856923140435984577574698574803934567774824230985421074605062371141877954182153046474983581941267398767559165543946077062914571196477686542167660429831652624386837205668069376");
	
	System.out.println("Making sure prime numbers are secure and random\n");
	
	//System.out.println("\n Public key is:\t"+publickey.toString());
	/*Check if prime numbers p and q are valid to use*/
	
	while(p.equals(q) ||abs.compareTo(compare)==-1 || abs2.compareTo(compare)==-1 && phi.gcd(e).compareTo(BigInteger.ONE) == 0 && e.compareTo(phi) < 0){
		p = BigInteger.probablePrime(len,r);
		q = BigInteger.probablePrime(len,r);
		abs = p.subtract(q);
		abs =abs.abs();
		abs2 = q.subtract(p);
		abs2 = abs2.abs();
		publickey=p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		System.out.println("Calculating new primes");
	}
	//Compute private key
	BigInteger d = e.modInverse(phi);
	
	System.out.println("Prime numbers are secure\n");
	System.out.println("\nPublic key is:\t"+publickey.toString());
	
	
	System.out.println("\nGenerating BigInteger for encryption...\n");
	
	//Generate random number to encrypt 
	
	BigInteger message = new BigInteger(256,r);
	
	System.out.println("Randomly generated BigInteger for encrypting is:\n\n"+message.toString());
	
	
	//Start timer for encryption
	
	long start = System.currentTimeMillis();
	
	
	//Encrypt message 1000 times
	
	BigInteger encrypted=message.modPow(e,publickey);
	for(int count =0; count<1000;count++){
		encrypted = message.modPow(e,publickey);
	}
	
	System.out.println("\nEncrypted message is:\n\n"+encrypted.toString());
	
	//End timer
	
	long encryptend = System.currentTimeMillis()-start;
	
	//Print timer result
	
	System.out.println("\nTime to encrypt message 1000 times:\n" + encryptend+"\tMilliseconds");
	
	//Start timer for decryption
	
	long startdecrypt = System.currentTimeMillis();
	
	System.out.println("\nDecrypted:\n"+encrypted.modPow(d,publickey));
	
	
	//End timer
	long elapsedTimeMillis = System.currentTimeMillis()-startdecrypt;
	
	//Print result of timer
	System.out.println("\nTime to decrypt:\n"+elapsedTimeMillis+"\tMilliseconds");
	}

	

}
