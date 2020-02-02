package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		//Colocando a máquina em US language
		Locale.setDefault(Locale.US);
		//instanciando um scanner
		Scanner sc = new Scanner(System.in);
		
		//pedindo a entrada do caminho do arquivo csv
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		//trywithresources
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			//criando uma lista de empregados
			List<Employee> list = new ArrayList<>();
			
			//lendo a primeira linha do arquivo csv
			String line = br.readLine();
			//enquanto a linha for diferente de nulo faça
			while(line != null) {
				System.out.println(line);
				
				//cortando as linhas em cada virgula e salvando em um array 
				String[] fields = line.split(",");
				//adicionando os dados a lista de empregados
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				
				//lendo a proxima linha
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			Double salary = sc.nextDouble();
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary));
			
			//filtrando a lista onde o salario for maior do q o valor digitado
			List<String> emails = list.stream().filter(p -> p.getSalary() > salary).map(p -> p.getEmail()).sorted().collect(Collectors.toList());
			
			//imprimindo os emails de cada empregado q receba acima do valor digitado
			emails.forEach(System.out::println);
			
			//filtrando a lista das pessoas q começam o nome com a letra M e depois somando os seus respectivos salarios
			double sum = list.stream().filter(p -> p.getName().charAt(0) == 'M').map(p -> p.getSalary()).reduce(0.0, (x,y) -> x + y);
			
			//imprimindo a soma dos salarios
			System.out.println("Sum of salary of people whose name starts with 'M': " + sum);
			
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}

}
