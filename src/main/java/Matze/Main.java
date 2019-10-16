package Matze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Matze.persistence.Localization;
import  Matze.service.Main_helper;

import Matze.Frame;
import Matze.Package;

public class Main {	

	/* Main Methode */
	public static void main(String[] args) throws IOException {		
		
		String answer;
		Frame chosenFrame = new Frame("","", "");
		int frameNr,packageNr;	
		ArrayList<Package> chosenPackageList = new ArrayList<Package>(1);
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
		/* haha */
		/* Frames einlesen*/
		  String line  = brFrames.readLine();		
		  while (line != null)
		   {
			 String[] lineValues = line.split(";");
			 Frame someFrame = new Frame(lineValues[0], lineValues[1], lineValues[2]);
			 framesList.add(someFrame);
			 line  = brFrames.readLine();
		   } 
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