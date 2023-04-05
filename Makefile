runTests: runAlgorithmEngineerTests runBackendDeveloperTests runDataWranglerTests runFrontendDeveloperTests

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTests
AlgorithmEngineerTests.class: AlgorithmEngineerTests.java EnhancedRedBlackTreeAE.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
EnhancedRedBlackTreeAE.class: EnhancedRedBlackTreeAE.java
	javac EnhancedRedBlackTreeAE.java

runBackendDeveloperTests: BackendTests.class
	java -jar junit5.jar -cp . --select-class=BackendTests

BackendTests.class: BackendTests.java College.class CollegeReader.class CollegeSearchBackendBD.class EnhancedRedBlackTree.class
	javac -cp .:junit5.jar BackendTests.java

College.class: College.java CollegeInterface.java
	javac College.java CollegeInterface.java

CollegeReader.class: CollegeReader.java CollegeReaderInterface.java
	javac CollegeReader.java CollegeReaderInterface.java

CollegeSearchBackendBD.class: CollegeSearchBackendBD.java  CollegeSearchBackendInterface.java
	javac CollegeSearchBackendBD.java CollegeSearchBackendInterface.java

EnhancedRedBlackTree.class:
	javac EnhancedRedBlackTreeAE.java

runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests
FrontendDeveloperTests.class: FrontendDeveloperTests.java CollegeSearchFrontendFD.class RedBlackTreeFD.class CollegeFD.class CollegeSearchBackendFD.class
	javac -cp .:junit5.jar FrontendDeveloperTests.java
CollegeSearchFrontendFD.class: CollegeSearchFrontendFD.java CollegeSearchFrontendInterface.java CollegeSearchBackendFD.class
	javac CollegeSearchFrontendFD.java CollegeSearchFrontendInterface.java
CollegeSearchBackendFD.class: CollegeSearchBackendFD.java CollegeSearchBackendInterface.java RedBlackTreeFD.class CollegeFD.class
	javac CollegeSearchBackendFD.java CollegeSearchBackendInterface.java
RedBlackTreeFD.class: RedBlackTreeFD.java SortedCollectionInterface.java 
	javac RedBlackTreeFD.java SortedCollectionInterface.java
CollegeFD.class: CollegeFD.java CollegeInterface.java
	javac CollegeFD.java CollegeInterface.java

runDataWranglerTests: DataWranglerTests.class test.csv testBlank.csv
	java -jar junit5.jar -cp . --select-class=DataWranglerTests
DataWranglerTests.class: DataWranglerTests.java CollegeDW.class CollegeReaderDW.class
	javac -cp .:junit5.jar DataWranglerTests.java
CollegeDW.class: CollegeDW.java CollegeInterface.java
	javac CollegeDW.java CollegeInterface.java
CollegeReaderDW.class:
	javac CollegeReaderDW.java CollegeReaderInterface.java

clean:
	rm *.class
