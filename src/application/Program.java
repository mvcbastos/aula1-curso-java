package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		// Inicialização de objetos
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Definição de variáveis importantes
		Date d;
		String dateTemp;
		double valPerHour;
		int hours;
		String incomeDateStr;
		
		// Início do programa
		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();
		
		Department dep = new Department(departmentName);
		
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Level: ");
		String level = sc.nextLine();
		WorkerLevel wl = WorkerLevel.valueOf(level);
		System.out.print("Base salary: ");
		double salary = sc.nextDouble();
		
		Worker w = new Worker(name, wl, salary, dep);
		
		System.out.print("How many contracts to this worker? ");
		int ncontracts = sc.nextInt();
		
		for(int i=0; i < ncontracts; i++) {
			
			sc.nextLine();
			
			System.out.printf("Enter contract #%d data: %n", i+1);
			System.out.print("Date (DD/MM/YYYY): ");
			dateTemp = sc.nextLine();
			d = sdf.parse(dateTemp);
			System.out.print("Value per hour: ");
			valPerHour = sc.nextDouble();
			System.out.print("Duration (Hours): ");
			hours = sc.nextInt();
			
			HourContract hc = new HourContract(d, valPerHour, hours);
			w.addContract(hc);
		}

		sc.nextLine(); /*Sempre que tiver algo diferente de um nextLine (nextDouble,
		por exemplo) e depois tiver um nextLine (como tem abaixo), tem que inserir
		um nextLine antes*/
		System.out.println("");
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		incomeDateStr = sc.nextLine();
		
		String[] vecDate = incomeDateStr.split("/");
		int month = Integer.parseInt(vecDate[0]);
		int year = Integer.parseInt(vecDate[1]);
		
		System.out.println("Name: " + w.getName());
		System.out.println("Department: " + w.getDepartment().getName());
		System.out.printf("Income for " + incomeDateStr + ": %.2f%n", w.income(year, month));
		
		sc.close();

	}

}
