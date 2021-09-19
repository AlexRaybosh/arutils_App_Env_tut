package tut;

import com.google.gson.JsonObject;

import arapp.AppEnv;
import arutils.util.JsonUtils;

public class Example02_VerySimpleBootstrapWithOverride {

	public static void main(String[] args) throws Exception {
		// Changed the bootstrap resource or file
		AppEnv.presetBootstrapResource("bootstrap-02-env-override.json");
		//What's my environment name
		System.out.println("My environment name is: "+AppEnv.envName());
		// Get the environment as json
		JsonObject myEnviroment = AppEnv.getMeta();
		
		String prettyJson=JsonUtils.prettyPrint(myEnviroment);
		System.out.println("And I can access:");
		System.out.println(prettyJson);
		
		String var=JsonUtils.getString(myEnviroment, "my nice json configuration","complex property", "finally");
		System.out.println(var);		

	}
/*
 Expect to produce: (note "Not a default, I'm a local 1!", instead of "I'm the default'"

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

Not a default, I'm a local 1!


 
 */
}
