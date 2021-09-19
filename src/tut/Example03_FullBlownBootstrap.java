package tut;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

import com.google.gson.JsonObject;

import arapp.AppEnv;
import arutils.util.JsonUtils;

public class Example03_FullBlownBootstrap {

	public static void main(String[] args) throws Exception {
		// Changed the bootstrap resource or file
		AppEnv.presetBootstrapResource("bootstrap-03-fullblown-bootstrap.json");
		//What's my environment name
		System.out.println("My environment name is: "+AppEnv.envName());
		// Get the environment as json
		JsonObject myEnviroment = AppEnv.getMeta();
		
		String prettyJson=JsonUtils.prettyPrint(myEnviroment);
		System.out.println("And I can access:");
		System.out.println(prettyJson);
		
		// I can encrypt and decrypt things		
		// Hope the secure bootstrap entry provided me with secured AES256/RSA parameters
		// Should really add salt to AES
		String msg="русское шпионское сообщение";
		byte[] encrypted=AppEnv.encryptAES(msg.getBytes(StandardCharsets.UTF_8));
		System.out.println("Encrypt \""+msg+"\" AES-256: "+Base64.getEncoder().encodeToString(encrypted));
		byte[] dec = AppEnv.decryptAES(encrypted);
		System.out.println("Decrypted AES-256: "+new String(dec,StandardCharsets.UTF_8));

		// For short messages, can use RSA, though RSA is mostly for signatures
		msg="密碼";
		encrypted=AppEnv.encryptPrivateRSA(msg.getBytes(StandardCharsets.UTF_8));
		System.out.println("Encrypted \""+msg+"\" RSA: "+Base64.getEncoder().encodeToString(encrypted));
		dec = AppEnv.decryptPublicRSA(encrypted);
		System.out.println("Decrypted RSA: "+new String(dec,StandardCharsets.UTF_8));
		
		byte[] sig = AppEnv.signSHA256PrivateRSA("my approved message".getBytes(StandardCharsets.UTF_8));
		byte[] sig1 = AppEnv.signSHA256PrivateRSA("my approved message1".getBytes(StandardCharsets.UTF_8));
		
		System.out.println("matching?: "+Arrays.equals(sig, sig1)+" "+Base64.getEncoder().encodeToString(sig)+" != "+Base64.getEncoder().encodeToString(sig1));
		System.out.println("matching?: "+Arrays.equals(sig, AppEnv.signSHA256PrivateRSA("my approved message".getBytes(StandardCharsets.UTF_8))));
		
		
		
	}
/*
Bootstrap entries get evaluated in their respective order, till a successful evaluation. In real life only few are needed 

Expect to produce: (note "Not a default, I'm a local 1!", instead of "I'm the default'"

BOOTSTRAP: non production environment detected
BOOTSTRAP: Empty result from {"propertiesEntryType":"SHELL_EVAL","shellArgument":"${PROPERTIES_PROVIDER}"}
BOOTSTRAP: ${HOME}/${APP_ENV}/test_props_provider.sh in {"propertiesEntryType":"SHELL_EVAL","shellArgument":"${HOME}/${APP_ENV}/test_props_provider.sh","abortOnExecutionError":true} failed to execute: /bin/bash: /home/alex//test_props_provider.sh: No such file or directory
BOOTSTRAP: ./test_props_provider.sh in {"propertiesEntryType":"FILE_EXEC","file":"./test_props_provider.sh","args":["skip","me"],"abortOnFileMissing":false,"abortOnFileNotExecutable":false,"abortOnExecutionError":true} failed to execute: Not going to do  skip me
BOOTSTRAP: ./test_props_provider.properties in {"propertiesEntryType":"FILE","file":"./test_props_provider.properties","abortOnLoadError":true,"abortOnFileMissing":false} is missing
My environment name is: localdev1
And I can access:
{
	"my nice json configuration" : {
		"a property" : "some value",
		"another property" : 42,
		"complex property" : {
			"finally" : "Not a default, I'm a local 1!"
		}
	}
}

Encrypt "русское шпионское сообщение" AES-256: ZwT3ArZp2jezSMSx77yy5HDG7LfwK9/Q+xAmgDSu3tkV4egeoYTcdSWt5z6PLUi3RShUJB+xodO5mNdGe4BJUQ==
Decrypted AES-256: русское шпионское сообщение
Encrypted "密碼" RSA: TQzBU2oE+LLNUTIi/FrJKvPAvcq76NDApxFSyj5gTce02RFelT0ds9ia5NwVV/0RtCrlPo4bEHdG6gyE+71nPOhBdAhXM/e3cmGorvD5uQ5vsgjsIYuTbBFxfMo+arplD/4CWw4FoNvlaJ1ECvsWCOPuO42gvaNXMA/SXOPGAM6mTU7/xJKTTne/BPOh9z9HcKBEWw7MxR4hiosDYhJ8/Xew5CAkn1Woyd4Ka79Rg3Y9vzflRsssLql6AJyquBnuj8uSRqXiAKaxBU/3Cic1J1G7ezqndDegxpnpMsb7RFSiQuY+Vs1W978UXFlbBKWj5zStVZEBD8X4yjHl14culw==
Decrypted RSA: 密碼
matching?: false oyakHqHXUMRMZzyU6Zb3HskZvD4WmgrivARpmZr4s+DXVoZEr1KpK5tiorj2RK4jwTlDvAXyw/Nv55HsyHxOs2Or2izMQsxxIl4/RNevTnxWY3T6GizfDetdZbqIk4MKQDOr9Gb9eN+mJjCUOm8dXQB5whbF8T4xJctbn6LxQuJYZ3DItYqoNwOiozX+dgvcZoLv00xn8XrRW5Ja289vKDah+ffqJNMnQ1+BF+GJ3B0JhhKXEpyJJc76e1hcJHkNu8G+BzR5w3BS0J26lNgSL3JAn9ZeHEygHq3j2/JlTHSuOZj8AHBlAjnKDOlGjPdf6ZWqMc+GHdjRFNk0KIkSIA== != MP4AOHe5HpxjnXc9gfsbqvfWflyEbUyYJmCBLJ/EOjDmUv9c+C/t5zJrVpf0DBcrSNdneL/SS+CzL35lJkogjylYr15lexMfo0ZwfBf6SELR3nfK2swd//XKs27OnE1N8ZCuByJUk/w2YJBUPMszhE6FB8P1iqeBi+K8M+RGpl8gv853d5Fz5mB5KdyAj7MFhKKaJasiLDHo2nZNf1SAeZo3juXqIIdRhL6nCHQhPxxJdsG0LOevwOUddk/cxwmlmNoU1V6faAzycCMVd6hbZbMiqi19OreGc/PSN0oFI/8kT5Yb23DnQttccFrWxvJGVZ5lNFiwGau3dcd4tgA77g==
matching?: true

 */
}
