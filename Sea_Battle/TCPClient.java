import java.net.*;
import java.io.*;
import java.util.Scanner;


public class TCPClient {
	// Размер игрового поля
   	static int fieldSize = 10;

    public static void main (String args[]) {
        Socket s = null;
        try {
        	// Подключаемся к серверу, используя "вшитый" порт и адрес
            int serverPort = 7896;
            String serverIp = "localhost";
            s = new Socket(serverIp, serverPort);
            System.out.println("Successful connection to the server (port: '7896', ip-adress: 'localhost')");

            // Создаем потоки ввода/вывода
            DataInputStream in = new DataInputStream( s.getInputStream());
            DataOutputStream out = new DataOutputStream( s.getOutputStream());

            // Для работы с вводом через консоль
            Scanner scanner = new Scanner(System.in);

           	//Поля двух игроков без кораблей
    		char[][] BattleField1 = new char[fieldSize][fieldSize];
    		char[][] BattleField2 = new char[fieldSize][fieldSize];

    		// Заполняем поля пустыми значениями
            generateFields(BattleField1, BattleField2);

            //Выводим на экран собственное поле и поле противника
            printFields(BattleField1, BattleField2);

            //Расстановка кораблей пользователем
            System.out.println("\nLet's plan how our ships will be located!\n");
            for (int i = 0; i < 10; i++){
            	System.out.println("Format: x y direction [where direction may be H or h (=horizontal), V or v (=vertical)]\n");
            	inputField(BattleField1, i, scanner);
            	printFields(BattleField1, BattleField2);
            }

            // Ожидание расстановки кораблей у противника
            int ready = 1;
            out.writeInt(ready);
            System.out.print("\nWaiting another player...\n");
            int readyEnemy = in.readInt();
            System.out.print("\nAnother player ready!\n");

            // Отслеживаем конец игры - 2 счётчика
            int counterEnemyHits = 0;
            int counterMyHits = 0;

            // Основная часть игры
            while(true) {
            	// Наш ход
                counterMyHits = myTurn(counterMyHits, BattleField1, BattleField2, in, out, scanner);
                if (checkEnd(counterMyHits, "myTurn"))
                	break;

                // Ход переходит противнику
                counterEnemyHits = enemyTurn(counterEnemyHits, BattleField1, BattleField2, in, out);
                if (checkEnd(counterEnemyHits, "enemyTurn"))
                    break;
            }
        } catch (UnknownHostException e) {
        	System.out.println("UnknownHostException:" + e.getMessage());
        } catch (EOFException e) {
        	System.out.println("EOFException:" + e.getMessage());
        } catch (IOException e) {
        	System.out.println("IOException:" + e.getMessage());
        } finally {
        	if (s != null)
        		try {
        			s.close();
        		} catch (IOException e) {
        			System.out.println("IOException [on close socket]: " + e.getMessage());}
        }
    }


   	// Создание пустых полей
    private static void generateFields(char[][] BattleField1, char[][] BattleField2)
    {
		for (int i = 0; i < fieldSize; i++)
        	for (int j = 0; j < fieldSize; j++)
            {
                BattleField1[i][j] = '\u25A0';  // □
                BattleField2[i][j] = '\u25A0';  // □
            }
    }


    // Отображение игровых полей на консоль
    private static void printFields(char[][] BattleField1, char[][] BattleField2)
    {
    	System.out.print("\n\tOur ships\t\t\t\t\t\tEnemy ships\n");
    	for (int j = 0; j < 2; j++) {
    		System.out.print("   ");
    		for (int i = 0; i < fieldSize; i++)
    			System.out.print(i + "  ");
    		System.out.print("\t\t\t");
    	}
    	System.out.print("\n");
        for (int i = 0; i < fieldSize; i++) {
        	System.out.print(i + "  ");
            for (int j = 0; j < fieldSize; j++) {
            	System.out.print(BattleField1[i][j] + "  ");
            }
            System.out.print("\t\t\t");
            System.out.print(i + "  ");
            for (int j = 0; j < fieldSize; j++) {
                System.out.print(BattleField2[i][j] + "  ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }


    // Заполнение поля кораблями. Учёт необходимых проверок.
    private static void inputField(char[][] BattleField1, int k, Scanner scanner)
    {
        if ((k >= 0) && (k < 4))
            System.out.print("Place 1-deck ship:\n");
        else if ((k >= 4) && (k < 7))
            System.out.print("Place 2-deck ship:\n");
        else if ((k >= 7) && (k < 9))
            System.out.print("Place 3-deck ship:\n");
        else if (k == 9)
            System.out.print("Place 4-deck ship:\n");

        // Получаем корректный ввод от пользователя
        int temp1 = -1, temp2 = -1;
        char orient = 'n';
        while(true) {
            try {
            	// Проверка на 3 полученных аругмента: x, y, Направление.
        	    String string = scanner.nextLine();
                String[] chunk = string.split(" ");
                if (chunk.length != 3) {
                	System.out.print("Incorrect input. 3 parameters are expected: x y direction.\n");
                	continue;
                }

                // Разбираем параметры на составляющие
                temp1 = Integer.parseInt(chunk[0]);
                temp2 = Integer.parseInt(chunk[1]);
                orient = chunk[2].charAt(0);

                // Проверка корретности заданного направления
                if ((orient != 'H') && (orient != 'h') && (orient != 'V') && (orient != 'v')) {
                    System.out.print("Unknown direction. Available: H (or h) - horizontal, V (or v) - vertical. Please try again.\n");
                	continue;
                }
                // Проверка корректности полученных координат
                if ((temp1 >= fieldSize) || (temp1 < 0) || (temp2 >= fieldSize) || (temp2 < 0) ) {
                    System.out.print("Incorrect coordinates. Make sure that you get to the playing field. Please try again.\n");
                    continue;
                }
                    
                // Проверка корректности относительно других кораблей
                int checkCorrectShip = 0;
                if ((orient == 'H') || (orient == 'h'))
                {
                    int len = 0;
                    try {
                    	len = getLen(temp1, k);
                    	checkCorrectShip = checkHorizontal(BattleField1, temp1, temp2, len);
                    } catch (ArithmeticException e) {
                    	System.out.print("The specified coordinates and direction do not allow you to place the ship on the playing field. Please try again.\n");
                    	continue;
                    }
                } else if ((orient == 'V') || ((orient == 'v')))
                {
                    int len = 0;
                    try {
                    	len = getLen(temp2, k);
                        checkCorrectShip = checkVertical(BattleField1, temp1, temp2, len);
                    } catch (ArithmeticException e) {
                    	System.out.print("The specified coordinates and direction do not allow you to place the ship on the playing field. Please try again.\n");
                    	continue;
                    }
                }

                if (checkCorrectShip == 0)
                    break;
                else {
                    if (checkCorrectShip == 2)
                        System.out.print("The ship intersects with other ships or does not have a gap to them of at least 1 cell.\n");
                    else
                        System.out.print("Out of range! Please try again\n");
                }
            } catch (NumberFormatException e) {
                System.out.print("NumberFormatException. Please try again\n");
            }
        } // end while(true)

        // Установка корабля
        if ((orient == 'H') || (orient == 'h'))
        	placeShipHorizontal(BattleField1, temp1, temp2, k);
        else if ((orient == 'V') || (orient == 'v'))
        	placeShipVertical(BattleField1, temp1, temp2, k);
    }


    // Получаем длину корабля на текущем шаге заполнения.
    private static int getLen(int temp, int k)
    {
    	int len = 0;
    	if ( k < 4)
			len = 1;
        else if (k < 7) 
		{
        	len = 2;
            if (temp + 1 >= fieldSize)
            	throw new ArithmeticException("The specified coordinates and direction do not allow you to place the ship on the playing field. Please try again.");
        }
        else if (k < 9)
		{
        	len = 3;
            if (temp + 2 >= fieldSize)
            	throw new ArithmeticException("The specified coordinates and direction do not allow you to place the ship on the playing field. Please try again.");
        }
        else if (k < 10) 
        {
        	len = 4;
            if (temp + 3 >= fieldSize)
            	throw new ArithmeticException("The specified coordinates and direction do not allow you to place the ship on the playing field. Please try again.");
        }
    	return len;
    }


    // Проверка на возможность установки корабля по горизонтали.
    private static int checkHorizontal(char[][] BattleField, int temp1, int temp2, int len)
    {
        int next = temp1;
		for (int l = 0; l < len; l++)
        {
        	if (BattleField[temp2][next] != '\u25A0')
           		return 2;
           	if ((next - 1 >= 0) && (BattleField[temp2][next - 1] != '\u25A0'))
                return 2;
            if ((temp2 - 1 >= 0) && (BattleField[temp2 - 1][next] != '\u25A0'))
                return 2;
			if ((next - 1 >= 0) && (temp2 - 1 >= 0) && (BattleField[temp2-1][next-1] != '\u25A0'))
                return 2;
            if ((next + 1 < fieldSize) && (BattleField[temp2][next + 1] != '\u25A0'))
                return 2;
            if ((temp2 + 1 < fieldSize) && (BattleField[temp2 + 1][next] != '\u25A0'))
                return 2;
            if ((next + 1 < fieldSize) && (temp2 + 1 < fieldSize) && (BattleField[temp2+1][next+1] != '\u25A0'))
                return 2;
            if ((next + 1 < fieldSize) && (temp2 - 1 >= 0) && (BattleField[temp2-1][next+1] != '\u25A0'))
                return 2;
            if ((next - 1 >= 0) && (temp2 + 1 < fieldSize) && (BattleField[temp2+1][next-1] != '\u25A0'))
                return 2;

        	next += 1;
      	}

      	return 0;
    }


	// Проверка на возможность установки корабля по вертикали.
    private static int checkVertical(char[][] BattleField, int temp1, int temp2, int len)
    {
    	int next = temp2;

        for (int l = 0; l < len; l++)
        {
        	if (BattleField[next][temp1] != '\u25A0')
            	return 2;
        	if ((temp1 - 1 >= 0) && (BattleField[next][temp1 - 1] != '\u25A0'))
            	return 2;
        	if ((next - 1 >= 0) && (BattleField[next - 1][temp1] != '\u25A0'))
				return 2;
			if ((temp1 - 1 >= 0) && (next - 1 >= 0) && (BattleField[next - 1][temp1 - 1] != '\u25A0'))
               	return 2;
			if ((temp1 + 1 < fieldSize) && (BattleField[next][temp1 + 1] != '\u25A0'))
            	return 2;
            if ((next + 1 < fieldSize) && (BattleField[next + 1][temp1] != '\u25A0'))
            	return 2;
            if ((temp1 + 1 < fieldSize) && (next + 1 < fieldSize) && (BattleField[next + 1][temp1 + 1] != '\u25A0'))
            	return 2;
            if ((next + 1 < fieldSize) && (temp1 - 1 >= 0) && (BattleField[next+1][temp1-1] != '\u25A0'))
               	return 2;
            if ((next - 1 >= 0) && (temp1 + 1 < fieldSize) && (BattleField[next-1][temp1+1] != '\u25A0'))
                return 2;

        	next += 1;
        }

        return 0;
    }


    // Установка корабля на игровом поле горизонтально.
    private static void placeShipHorizontal(char[][] BattleField, int temp1, int temp2, int k)
    {
    	if (k < 4)
            BattleField[temp2][temp1] = '1';
        else if (k < 7)
        {
        	BattleField[temp2][temp1] = '1';
            BattleField[temp2][temp1+1] = '1';
        }
		else if (k < 9)
        {
        	BattleField[temp2][temp1] = '1';
            BattleField[temp2][temp1+1] = '1';
			BattleField[temp2][temp1+2] = '1';
        }
        else if (k < 10)
        {
        	BattleField[temp2][temp1] = '1';
          	BattleField[temp2][temp1+1] = '1';
           	BattleField[temp2][temp1+2] = '1';
            BattleField[temp2][temp1+3] = '1';
        }
    }


    // Установка корабля на игровом поле вертикально.
    private static void placeShipVertical(char[][] BattleField, int temp1, int temp2, int k)
	{
		if (k < 4)
            BattleField[temp2][temp1] = '1';
        else if (k < 7)
        {
        	BattleField[temp2][temp1] = '1';
            BattleField[temp2+1][temp1] = '1';
        }
        else if (k < 9)
        {
			BattleField[temp2][temp1] = '1';
            BattleField[temp2+1][temp1] = '1';
        	BattleField[temp2+2][temp1] = '1';
       	}
        else if (k < 10)
        {
        	BattleField[temp2][temp1] = '1';
            BattleField[temp2+1][temp1] = '1';
            BattleField[temp2+2][temp1] = '1';
            BattleField[temp2+3][temp1] = '1';
        }
    }


    // Обработчик наших ходов
    private static int myTurn(int counter, char[][] BattleField1, char[][] BattleField2, DataInputStream in, DataOutputStream out, Scanner scanner)
    {
    	try {
    		int response = 1;
    		System.out.print("\n\n==========================================================\n");
        	System.out.print("\t\tIt's our turn to go!");
        	System.out.print("\n==========================================================\n");
        	while (response == 1) {
        		// Ввод координат, куда будет выполняться ход и проверка корректности
           		String myTurn;
            	int temp1, temp2;
            	while(true) {
            		try {
            			System.out.print("Coordinates (format x y): ");
                		myTurn = scanner.nextLine();
                  		String[] chunk = myTurn.split(" ");
                  		if (chunk.length != 2) {
                			System.out.print("Incorrect input. 2 parameters are expected.\n");
                			continue;
                		}
                    	temp1 = Integer.parseInt(chunk[0]);
                    	temp2 = Integer.parseInt(chunk[1]);
                    	if ((temp1 >= 0) && (temp1 < fieldSize) && (temp2 >= 0) && (temp2 < fieldSize))
                        	break;
                    	else
                        	System.out.print("Out of range! Please try again\n");
                	}
                	catch (NumberFormatException e) {
                    	System.out.print("NumberFormatException. Please try again\n");
                	}
            	}

            	// Отправка хода противнику и получение ответа от противника - попал/не попал
            	out.writeUTF(myTurn);
				response = in.readInt();

				// Вывод информации и обновление поля
            	if (response == 0) {
            		BattleField2[temp2][temp1] = '~';
            		printFields(BattleField1, BattleField2);
            		System.out.print("We missed!\n\n");
            	}
            	else if (response == 1 ) {
                	BattleField2[temp2][temp1] = 'X';
                	printFields(BattleField1, BattleField2);
            		System.out.print("We hit the enemy!\n\n");
                	counter++;
            	}
            	else if (response == 2) {
            		printFields(BattleField1, BattleField2);
            		System.out.print("Whoops, we've already fired at these coordinates!\n\n");
            	}
            	if (counter == 20)
               		break;
        	}
        	return counter;
        } catch (IOException e) { 
        	System.out.println("readline:" + e.getMessage());
        	return -1;
        }
    }


    // Обработчик ходов противника
    private static int enemyTurn(int counter, char[][] BattleField1, char[][] BattleField2, DataInputStream in, DataOutputStream out)
    {
    	try {
    		int response = 1;
    		System.out.print("\n\n==========================================================\n");
        	System.out.print("\t\tThe enemy makes Turn...");
        	System.out.print("\n==========================================================\n");
        	while (response == 1) {
        		// Получаем координаты, куда делает ход противник
        		String enemyTurn = in.readUTF();
        		String[] chunk = enemyTurn.split(" ");
        		int temp1 = Integer.parseInt(chunk[0]);
            	int temp2 = Integer.parseInt(chunk[1]);

            	// Обрабатываем все возможные ситуации
           		if (BattleField1[temp2][temp1] == '\u25A0') {
           			BattleField1[temp2][temp1] = '~';
           			printFields(BattleField1, BattleField2);
            		System.out.print("The enemy missed!\n");
               		response = 0;
           		} else if (BattleField1[temp2][temp1] == 'X' || BattleField1[temp2][temp1] == '~') {
            		printFields(BattleField1, BattleField2);
                	System.out.print("The enemy has already fired here!\n");
                	response = 2;
            	} else if (BattleField1[temp2][temp1] == '1') {
                	BattleField1[temp2][temp1] = 'X';
                	printFields(BattleField1, BattleField2);
               		System.out.print("The enemy hit our ship!\n");
                	counter++;
                	response = 1;
            	}
            	out.writeInt(response);
            	if (counter == 20)
               		break;
        	}
        	return counter;
    	} catch (IOException e) { 
    		System.out.println("readline:" + e.getMessage()); 
    		return -1;
    	}
    }


    // Проверка окончания игры && подведение итогов
    private static boolean checkEnd(int count, String turn)
    {
    	if (count == 20 && turn == "myTurn") {
    		System.out.print("\n\n=========================VICTORY==========================\n");
        	System.out.print("\tHurray! We've sunk all the enemy ships!");
        	System.out.print("\n==========================================================\n");
        	return true;
    	}
        else if (count == 20 && turn == "enemyTurn") {
        	System.out.print("\n\n=========================DEFEAT===========================\n");
        	System.out.print("\tAll our ships are sunk!");
        	System.out.print("\n==========================================================\n");
        	return true;
        }
        return false;
    }
}