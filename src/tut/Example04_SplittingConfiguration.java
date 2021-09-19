package tut;

import com.google.gson.JsonObject;

import arapp.AppEnv;
import arutils.util.JsonUtils;

public class Example04_SplittingConfiguration {

	public static void main(String[] args) throws Exception {
		// Changed the bootstrap resource or file
		AppEnv.presetBootstrapResource("bootstrap-04-split_to_files.json");
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
Bootstrap entries get evaluated in their respective order, till a successful evaluation. In real life only few are needed 

Expect to produce: (Im my_conf2)

BOOTSTRAP: Configuration my_conf1 contains invalid include entry "my_conf3", skipping it
My environment name is: localdev1
And I can access:
{
	"my nice json configuration" : {
		"a property" : "some value",
		"another property" : 42,
		"complex property" : {
			"finally" : "Im my_conf2"
		}
	}
}

Im my_conf2


 */
}
