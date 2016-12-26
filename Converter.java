import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Converter{
		//dichiarazioni per file di output
		static FileWriter w;
		static BufferedWriter out;
		
		//dichiarazione per input da tastiera
		static Scanner s = new Scanner(System.in);
			
		//dichiarazioni per file di output
		static File x;
		static Scanner in;
		static int parentesiAperte = 0;
		
		static String tipoMetodo;
		static String tmp, tmp2, tmp3;
		
		static String nomeClasse;
		static int numeroMetodi;
		static String stringa;
		
		static ArrayList<String> metodi = new ArrayList<String>();
		static ArrayList<String> argomentiMetodo = new ArrayList<String>();
	
	
	
	
	public static void main(String[] args)
	{
		
		
		/*//apertura file sorgente e creazione file di output
		try
		{
			System.out.println("Insere percorso file: ");
			String path = s.next();
			in = new FileInputStream(path);
			out = new Formatter("UML.txt");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File non è stato trovato. Sei sicuro che il percorso inserito sia giusto?");
		}*/
		
		
		try
		{
			System.out.println("Insere percorso file: ");
			String path = s.next();
			x = new File(path);
			in = new Scanner(x);
			
			w = new FileWriter("UML.txt");
			out = new BufferedWriter (w);
			
			//FULCRO DEL PROGRAMMA
			while(in.hasNext())
			{
				
				String caso;
				switch(caso = in.next())
				{
					case "class":
					    tmp = in.next();
					    if(tmp.contains("{"))
					    {
					        tmp = tmp.substring(0, tmp.length()-1);
					        nomeClasse = tmp;
					        parentesiAperte++;
					    }

						out.write("\t" + tmp);
						out.newLine();
						out.write("------------------------------------");
						out.newLine();
						break;
					case "{":
						parentesiAperte++;
						tmp = tmp2;
						tmp2 = tmp3;
						tmp3 = caso;
						break;
					case "}":
						parentesiAperte--;
						tmp = tmp2;
						tmp2 = tmp3;
						tmp3 = caso;
						break;
					case "public":
						out.write("+");
						tmp = tmp2;
						tmp2 = tmp3;
						tmp3 = caso;
						break;
					case "private":
						out.write("-");
						tmp = tmp2;
						tmp2 = tmp3;
						tmp3 = caso;
						break;
					case "static":
						if(in.hasNext())
							tipoMetodo = in.next();
						tmp = tmp2;
						tmp2 = tmp3;
						tmp3 = caso;
						break;
						
					default:
						tmp = tmp2;
						tmp2 = tmp3;
						tmp3 = caso;
						
						if(caso.contains("(") && (caso.length() > 1))
						{
							if(nomeClasse == (caso = caso.substring(0, caso.length()-1)))
							{
								
							}
							
						}
						else
						{	
							if(tmp3.equals("("))
							{
								tipoMetodo = tmp;
								stringa = tmp2 + tmp3;
								do
								{
									if(in.hasNext())
									{
										caso = in.next();
										tmp = tmp2;
										tmp2 = tmp3;
										tmp3 = caso;
										
										
										
										//if per i parametri
										if(tmp3.contains(",") && (tmp3.length() > 1))
										{
											stringa += tmp3.substring(0, tmp3.length()-1) + ":" + tmp2 + ",";
										}
										else
										{
											if(tmp3.equals(","))
											{
												stringa += tmp2 + ":" + tmp + tmp3;
											}
											else
											{	
												//controlli per la fine della dichiarazione parametri
												if(tmp3.contains(")") && (tmp3.length() > 1))
												{
													if(!(tmp3.contains("{")))
														stringa += tmp3.substring(0, tmp3.length()-1) + ":" + tmp2 + ")";
													else
														if(tmp3.equals("){"))
															stringa += tmp2 + ":" + tmp + ")";
														else
															stringa += tmp3.substring(0, tmp3.length()-2) + ":" + tmp2 + ")";
												}
												else
												{
													if(tmp3.equals(")"))
													{
														stringa += tmp2 + ":" + tmp + tmp3;
													}
												}
											}
										}
										
										
									}
									
									
									//controllare se c'è anche un costruttore
									
									
									
								}while(!(tmp3.contains(")")));
								
								if(tmp3.contains("{"))
								{
									int parentesi = 1;
									
									do
									{
										if(in.hasNext())
										{
											String next = in.next();
											if(next.contains("}"))
												parentesi--;
											else
												if(next.contains("{"))
													parentesi++;
										}
									}while(parentesi > 0);
								}
								else
								{
									String next = in.next();
									if(next.contains("{"))
									{
										int parentesi = 1;
										
										do
										{
											if(in.hasNext())
											{
												next = in.next();
												if(next.contains("}"))
													parentesi--;
												else
													if(next.contains("{"))
														parentesi++;
											}
										}while(parentesi > 0);
									}
								}
								stringa += ":" + tipoMetodo;
								out.write(stringa);
								metodi.add(stringa);
							}
							
							
							
							
							
							
							//CODICE IN CASO DI DICHIARAZIOE ATTRIBUTO
							//codice per il caso in cui c'è lo spazio tra il nome dell'attributo e il punto e virgola
							if(tmp3.equals(";"))
							{
								out.write(tmp2 + ":" + tmp);
								out.newLine();
							}
							else
							{	//codice per quando il nome dell'attributo e il punto e virgola sono attaccati
								if(tmp3.contains(";"))
								{
									tmp3 = tmp3.substring(0, tmp3.length()-1);
									out.write(tmp3 + ":" + tmp2);
									out.newLine();
								}
							}
								
						}
				}
				
				
			}
			
			
			
			out.flush();
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.exit(-1);
		}
	}
}
