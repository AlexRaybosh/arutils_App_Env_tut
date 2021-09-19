# Application Encironment tutorial
self contained Eclipse project

[Files](https://github.com/AlexRaybosh/arutils_App_Env_tut/tree/master/src/tut)


Example01_SomeBitsOfConfiguration.java
Example02_VerySimpleBootstrapWithOverride.java
Example03_FullBlownBootstrap.java
Example04_SplittingConfiguration.java
Example05_TickTockSubsystemAndEnvReload.java
Example06_ProcSubSystemInDB.java
Example07_DictionaryForCommonWords.java
TickTockSubSystem.java


Bootstrap configurations (bootstrap*.json) are int [src root](https://github.com/AlexRaybosh/arutils_App_Env_tut/tree/master/src), so should get loaded as a class loader resource,

Only one bootstrap.json file is needed for a reall application. Best to split, and have all listed once, e.g. [allincludes.jason](https://github.com/AlexRaybosh/arutils_App_Env_tut/blob/master/src/allincludes.json)
 
