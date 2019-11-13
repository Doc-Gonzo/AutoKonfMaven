package Matze.app;

import Matze.AppConfiguration;
import Matze.Controller.Main_helper;
import Matze.Model.Frame;
import Matze.Model.Package;
import Matze.persistence.Localization;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication

public class Main extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(AppConfiguration.class);
	}

	/* Main Methode */
	public static void main(String[] args) throws IOException {

		SpringApplication application = new SpringApplication(Main.class);
		application.setApplicationContextClass(AnnotationConfigWebApplicationContext.class);
		SpringApplication.run(Main.class, args);

		
		String answer;
		Frame chosenFrame;
		int frameNr,packageNr;	
		ArrayList<Package> chosenPackageList;
		boolean frameSure = false,packageSure = false,packageFull = false;
		int lastOrderNumber = 0;
		Scanner scan = new Scanner(System.in);
		Localization german = new Localization();		
		FileReader readerFrames = null,readerPackages = null;
		Main_helper helper = new Main_helper();
		
		/* FileReader erstellen */
		readerFrames = helper.startFrameReader("frames.csv");
		readerPackages = helper.startPackageReader("packages.csv");		
		BufferedReader brFrames = new BufferedReader(readerFrames);
		BufferedReader brPackages = new BufferedReader(readerPackages);
		
		/* Arraylists erstellen */
		ArrayList<Frame> framesList = new ArrayList<>(1);
		ArrayList<Package> packagesList = new ArrayList<>(1);

		/* Frames einlesen*/
		  String line  = brFrames.readLine();		
		  while (line != null)
		   {
			 String[] lineValues = line.split(";");
			 Frame someFrame = new Frame(lineValues[0], lineValues[1], lineValues[2]);
			 framesList.add(someFrame);
			 line  = brFrames.readLine();
		   }
		helper.addFramestoDB(framesList);

		/* Packages einlesen*/
		  line  = brPackages.readLine();		
		  while (line != null)
		   {
			 String[] lineValues = line.split(";");
			 Package somePackage = new Package(lineValues[0], lineValues[1], lineValues[2], lineValues[3]);
			 packagesList.add(somePackage);
			 line  = brPackages.readLine();
		   } 
		
		/* Start*/
		System.out.print(german.hello);
		System.out.print(german.breaks);
		chosenFrame = helper.chooseFrame(framesList);

		/* Pakete waehlen */
		while(!packageSure) {
			/* zuvor gewaehlten Rahmen anzeigen*/
			System.out.print(german.pickedFrame + german.space + chosenFrame.getFrameName() + german.space + german.delTime + chosenFrame.getFrameLieferZeit() + german.days + german.space + german.price + chosenFrame.getFramePreis() + german.breaks);
			for(int i = 0; i<2; i++) {
				System.out.print(german.breaks);
			}			
			
			System.out.print(german.chosePackage);
			System.out.print(german.breaks);

			chosenPackageList = helper.choosePackages(packagesList);
			packageSure = true;
			for(int i = 0; i<8; i++) {System.out.print(german.breaks);}
			System.out.print(german.pickedFrame + german.breaks + german.space + chosenFrame.getFrameName() + german.space + german.delTime + chosenFrame.getFrameLieferZeit() + german.days + german.space + german.price + chosenFrame.getFramePreis() + german.breaks);

			System.out.print(german.breaks);
			System.out.print(german.pickedPackage + "\n \n");
			for(Package package2 : chosenPackageList) {				
				System.out.print(german.space + package2.getPackageName() + german.space + german.price + package2.getPackagePreis() + german.space + german.delTime + package2.getPackageLieferZeit() + german.breaks);

			}
			/* gib Bestellung aus */
			helper.statusZeile(chosenFrame,chosenPackageList);
			scan.close();
		}
	}
}