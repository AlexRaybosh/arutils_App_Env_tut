# Application Environment tutorial
self contained Eclipse project (Java 11)

Based on [https://github.com/AlexRaybosh/arutils](https://github.com/AlexRaybosh/arutils)<br>


Tutorial java [Files](https://github.com/AlexRaybosh/arutils_App_Env_tut/tree/master/src/tut) <br/>


Example01_SomeBitsOfConfiguration.java - loads [bootstrap.json](https://github.com/AlexRaybosh/arutils_App_Env_tut/blob/master/src/bootstrap.json)<br/>
Example02_VerySimpleBootstrapWithOverride.java - loads [bootstrap-02-env-override.json](https://github.com/AlexRaybosh/arutils_App_Env_tut/blob/master/src/bootstrap-02-env-override.json)<br/>
Example03_FullBlownBootstrap.java - loads [bootstrap-03-fullblown-bootstrap.json](https://github.com/AlexRaybosh/arutils_App_Env_tut/blob/master/src/bootstrap-03-fullblown-bootstrap.json)<br/>
Example04_SplittingConfiguration.java - loads [bootstrap-04-split_to_files.json](https://github.com/AlexRaybosh/arutils_App_Env_tut/blob/master/src/bootstrap-04-split_to_files.json)<br/>
Example05_TickTockSubsystemAndEnvReload.java - loads [bootstrap-05-tick-tock.json](https://github.com/AlexRaybosh/arutils_App_Env_tut/blob/master/src/bootstrap-05-tick-tock.json)<br/>
Example06_ProcSubSystemInDB.java - loads [bootstrap-06-with-proc-and-db.json](https://github.com/AlexRaybosh/arutils_App_Env_tut/blob/master/src/bootstrap-06-with-proc-and-db.json)<br/>
Example07_DictionaryForCommonWords.java - loads [bootstrap-07-dictionary.json](https://github.com/AlexRaybosh/arutils_App_Env_tut/blob/master/src/bootstrap-07-dictionary.json)<br/>
TickTockSubSystem.java - an example subsystem with 2 timers<br/>


Bootstrap configurations (bootstrap*.json) are int [src root](https://github.com/AlexRaybosh/arutils_App_Env_tut/tree/master/src), so should get loaded as a class loader resource,

Only one bootstrap.json file is needed for a reall application. Best to split, and have all listed at once, e.g. [allincludes.json](https://github.com/AlexRaybosh/arutils_App_Env_tut/blob/master/src/allincludes.json)
 
