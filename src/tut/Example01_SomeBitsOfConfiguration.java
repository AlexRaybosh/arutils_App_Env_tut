package tut;

import com.google.gson.JsonObject;

import arapp.AppEnv;
import arutils.util.JsonUtils;

public class Example01_SomeBitsOfConfiguration {

	public static void main(String[] args) throws Exception {
		
		//What's my environment name
		System.out.println("My environment name is: "+AppEnv.envName());
		// Get the environment as json
		JsonObject myEnviroment = AppEnv.getMeta();
		
		String prettyJson=JsonUtils.prettyPrint(myEnviroment);
		System.out.println("And I can access:");
		System.out.println(prettyJson);
		String var=JsonUtils.getString(myEnviroment, "my nice json configuration","complex property", "finally");
		System.out.println(var);

/*
 Expect to produce (from bootstrap.json):
 		
My environment name is: undefined
And I can access:
{
	"my nice json configuration" : {
		"a property" : "some value",
		"another property" : 42,
		"complex property" : {
			"finally" : "I'm the default'"
		}
	}
}

I'm the default'


 */
	}

}
