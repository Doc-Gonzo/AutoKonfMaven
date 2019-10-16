package Matze.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import Matze.Frame;
import Matze.Package;
import Matze.service.Main_helper;
import Matze.persistence.Localization;


public class Main_helper {
	 Localization german = new Localization();
	 Scanner scan = new Scanner(System.in);
	
	/* FileReader starten */
	public FileReader startFrameReader(String path_to_csv) {
		FileReader readerFrames = null;
		try {
			readerFrames = new FileReader(path_to_csv);
		
		} catch (FileNotFoundException e) {
			System.out.print("No Configfile found. Please contact Administrator");
		}
		return readerFrames;
	}
	
	
	public FileReader startPackageReader(String path_to_csv) {
		FileReader readerPackages = null;
		try {
			readerPackages = new FileReader(path_to_csv);
		} 
		catch (FileNotFoundException e) {
			System.out.print("No Configfile found. Please contact Administrator");
		}
		return readerPackages;
	}
	
	/* Gib Liste der Rahmen */
	public void giveFramesList(ArrayList<Frame> framesList) {
		int counter = 1;
		for(Frame frame : framesList) {
			System.out.print("(" + counter + ")" + german.space + frame.getFrameName() + german.space + german.delTime + frame.getFrameLieferZeit() + german.days + german.space + german.price + frame.getFramePreis() + german.breaks);
			counter ++;
		}
	}
	/* haha*/
	/* Gib Liste der Pakete */
	public void givePackagesList(ArrayList<Package> packagesList) {
		int counter = 1;
		for(Package packages : packagesList) {
			System.out.print("(" + counter + ")"+ german.space + packages.getPackageName() + german.space + packages.getPackageItems() + german.space + german.delTime + packages.getPackageLieferZeit() + german.space + german.price + packages.getPackagePreis() + german.breaks );
			counter ++;
		}		
	}
	
	/* Rahmen waehlen */
	public Frame chooseFrame(ArrayList<Frame> framesList) {
		boolean frameSure = false;
		int frameNr = 0;
			
			while(!frameSure) {
				System.out.print(german.choseFrame);
				System.out.print(german.breaks);
					
				/* Rahmen auflisten */
				giveFramesList(framesList);
					
				/* Rahmen waehlen */
				System.out.print(german.frameNr);
				frameNr = scan.nextInt();
				System.out.print(german.areYouSure);
				String answer = scan.next();
				if (answer.equalsIgnoreCase("Ja") || answer.equalsIgnoreCase("J") || answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes") ){
					/* Gewaehlten Frame eintragen */				
					frameSure = true;
					for(int i = 0; i<8; i++) {
						System.out.print(german.breaks);
					}
				}
				else {
					for(int i = 0; i<8; i++) {
						System.out.print(german.breaks);
					}
				}
			}
			return framesList.get(frameNr -1);	
	}
	
	/* Pakete waehlen */
	public ArrayList<Package> choosePackages(ArrayList<Package> packagesList) {

		ArrayList<Package> chosenPackageList = new ArrayList<>();	
		boolean packageFull = false;
		
		while(!packageFull) {
			Scanner scan = new Scanner(System.in);
			/* Pakete auflisten */
			givePackagesList(packagesList);
			
			/* Paket waehlen */
			System.out.print(german.packageNr);
			Integer packageNr = scan.nextInt();
			System.out.print(german.areYouSure);
			String answer = scan.next();
			if (answer.equalsIgnoreCase("Ja") || answer.equalsIgnoreCase("J") || answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes") ){
				/* Gewaehltes Paket eintragen */
				chosenPackageList.add(packagesList.get(packageNr -1));
				/* Gewaehltes Paket aus temporaerer Liste nehmen */
				packagesList.remove(packageNr - 1);
				for(int i = 0; i<8; i++) {System.out.print(german.breaks);}
			}
			else {
				for(int i = 0; i<8; i++) {System.out.print(german.breaks);}
			}
			/* Weiteres Paket Waehlen */
			System.out.print(german.anotherPackage);
			answer = scan.next();
			System.out.print(german.breaks);
			if (answer.equalsIgnoreCase("Ja") || answer.equalsIgnoreCase("J") || answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes") ){
				/* repeat loop */					
			}
			else {
				packageFull = true;
			}			
		}
			return  chosenPackageList;	
	}
	
	
	

	/* Preis & Lieferzeit berechnen */
	public void statusZeile(Frame chosenFrame,ArrayList<Package> packagesList2) {
		
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		int gesamtPreis = Integer.parseInt(chosenFrame.getFramePreis());
		int lieferTage = Integer.parseInt(chosenFrame.getFrameLieferZeit());
		Date lieferDatum = new Date();
		
		/* Kalender mit heutigem Datum */
		Calendar c = Calendar.getInstance();
	    c.setTime(lieferDatum);		
		
	    /* Paket-Preis und Zeit Addieren*/
		for(Package packages : packagesList2) {
			gesamtPreis = gesamtPreis + Integer.parseInt(packages.getPackagePreis());
			lieferTage = lieferTage +  Integer.parseInt(packages.getPackageLieferZeit());
		}
		 c.add(Calendar.DATE, lieferTage);
		lieferDatum = c.getTime();
		System.out.println("Gesamtpreis: " + gesamtPreis +  "€."  + german.space + " Vorraussichtliche Lieferung: " + dateFormat.format(lieferDatum));
	}

}
