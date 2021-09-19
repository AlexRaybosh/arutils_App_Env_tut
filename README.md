# Application Environment tutorial
self contained Eclipse project

Based on [https://github.com/AlexRaybosh/arutils](https://github.com/AlexRaybosh/arutils)<br>


Tutorial [Files](https://github.com/AlexRaybosh/arutils_App_Env_tut/tree/master/src/tut) <br/>


Example01_SomeBitsOfConfiguration.java<br/>
Example02_VerySimpleBootstrapWithOverride.java<br/>
Example03_FullBlownBootstrap.java<br/>
Example04_SplittingConfiguration.java<br/>
Example05_TickTockSubsystemAndEnvReload.java<br/>
Example06_ProcSubSystemInDB.java<br/>
Example07_DictionaryForCommonWords.java<br/>
TickTockSubSystem.java<br/>


Bootstrap configurations (bootstrap*.json) are int [src root](https://github.com/AlexRaybosh/arutils_App_Env_tut/tree/master/src), so should get loaded as a class loader resource,

Only one bootstrap.json file is needed for a reall application. Best to split, and have all listed once, e.g. [allincludes.jason](https://github.com/AlexRaybosh/arutils_App_Env_tut/blob/master/src/allincludes.json)
 
