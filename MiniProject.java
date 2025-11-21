    import java.util.InputMismatchException;
    import java.util.Scanner;

    class Colors 
    {
        public static final String RESET  = "\u001B[0m";
        public static final String RED    = "\u001B[31m";
        public static final String GREEN  = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE   = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN   = "\u001B[36m";
        public static final String WHITE  = "\u001B[37m";
    }

    abstract class Restaurant 
    {
        abstract void VegMenu();
        abstract void NonvegMenu();
        abstract void DessertsMenu();
        abstract void BeveragesMenu();

        private String userName;
        private String password;

        Restaurant(String userName, String password) 
        {
            this.userName = userName;
            this.password = password;
        }

        public String getUserName() 
        { 
        	return userName; 
        }
        public void setUserName(String userName) 
        { 
        	this.userName = userName; 
        }

        public String getPassword() 
        { 
        	return password; 
        }
        public void setPassword(String password) 
        { 
        	this.password = password; 
        }
    }

    class A extends Restaurant
    {
        static Scanner sc = new Scanner(System.in);
        static double TotalPrice = 0.0;

      
        static String username() 
        {
        	System.out.println();
            System.out.print(Colors.CYAN + "Enter User name : " + Colors.RESET);
            String un = sc.next().toLowerCase();
            return checkUsernameLength(un);
        }

        static String checkUsernameLength(String un) 
        {
            if (un.length() >= 10)
            {
                return checkGmailFormat(un);
            } 
            else 
            {
                System.out.println(Colors.RED + "Set User Name at least 10 characters." + Colors.RESET);
                return username();
            }
        }

        static String checkGmailFormat(String un) 
        {
            if (un.endsWith("@gmail.com")) 
            {
                System.out.println(Colors.GREEN + " You entered a valid email." + Colors.RESET);
                return un;
            } else 
            {
                System.out.println(Colors.RED + "Username must end with '@gmail.com'." + Colors.RESET);
                return username();
            }
        }

        static String password() 
        {
            System.out.println();
            System.out.print(Colors.CYAN + "Set a strong password : " + Colors.RESET);
            String pw = sc.next();
            return checkPasswordLength(pw);
        }

        static String checkPasswordLength(String pw) 
        {
            if (pw.length() > 7) 
            {
                return checkUpperCase(pw);
            } 
            else 
            {
                System.out.println(Colors.RED + "Please Maintain Your Password at least 8 characters." + Colors.RESET);
                return password();
            }
        }

        static String checkUpperCase(String pw) 
        {
            if (Character.isUpperCase(pw.charAt(0)))
            {
                return checkDigit(pw);
            } 
            else 
            {
                System.out.println(Colors.RED + "First letter of your Password must be Uppercase." + Colors.RESET);
                return password();
            }
        }

        static String checkDigit(String pw)
        {
            int count = 0;
            for (int i = 0; i < pw.length(); i++) 
            {
                if (Character.isDigit(pw.charAt(i))) 
                	count++;
            }
            if (count >= 2)
            {
                System.out.println(Colors.GREEN + " Strong Password set successfully." + Colors.RESET);
                return pw;
            } 
            else 
            {
                System.out.println(Colors.RED + "Maintain at least 2 digits in your Password." + Colors.RESET);
                return password();
            }
        }

        A(String name, String pass) 
        {
            super(name, pass);
            System.out.println(Colors.GREEN + " Account Created Successfully! " + Colors.RESET);
        }

        void Login(A obj) 
        {
        	System.out.println(Colors.CYAN + "Please Login " + Colors.RESET);
            System.out.print(Colors.CYAN + "Enter Your User name: " + Colors.RESET);
            String uname = sc.next().toLowerCase();
            System.out.print(Colors.CYAN + "Enter Your Password: " + Colors.RESET);
            String pow = sc.next();
            if (obj.getUserName().equals(uname) && obj.getPassword().equals(pow)) 
            {
                System.out.println(Colors.GREEN + "Login Successful..." + Colors.RESET);
                obj.UpdateCredentials(obj);
            } 
            else
            {
                System.out.println(Colors.RED + " Invalid Login Details, Try Again!" + Colors.RESET);
                obj.Login(obj);
            }
        }

     
        void UpdateCredentials(A obj) 
        {
        	System.out.println();
            System.out.println(Colors.YELLOW + "If You Want to change User Name or Password." + Colors.RESET);
            System.out.println("1. Change User Name");
            System.out.println("2. Change Password");
            System.out.println("0. Continue to Menu");

            try 
            {
                System.out.print("Choose Your Choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: 
                    		obj.NewUserName(obj); 
                    		break;
                    case 2: 
                    		obj.NewPassword(obj); 
                    		break;
                    case 0: 
                    		return;
                    default: 
	                    	System.out.println(Colors.RED + "Invalid Choice." + Colors.RESET); 
	                    	UpdateCredentials(obj);
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println(Colors.RED + "Enter only numbers!" + Colors.RESET);
                sc.nextLine();
                UpdateCredentials(obj);
            }
        }

        void NewUserName(A obj) 
        {
            String newuname = username();
            obj.setUserName(newuname);
            System.out.println(Colors.GREEN + " User Name Updated Successfully..." + Colors.RESET);
        }

        void NewPassword(A obj) 
        {
            String newPassword = password();
            obj.setPassword(newPassword);
            System.out.println(Colors.GREEN + " Password Updated Successfully..." + Colors.RESET);
        }

   
        static void MenuList(Restaurant obj) 
        {
        	System.out.println();
            System.out.println(Colors.BLUE + "========== MENU ==========" + Colors.RESET);
            System.out.println("1. Veg Menu");
            System.out.println("2. Nonveg Menu");
            System.out.println("3. Desserts Menu");
            System.out.println("4. Beverages Menu");
            System.out.println("0. Exit from Menu");

            try {
                System.out.print(Colors.CYAN + "Enter a choice: " + Colors.RESET);
                int choice = sc.nextInt();
                switch (choice) 
                {
                    case 1:
	                    	obj.VegMenu(); 
	                    	break;
                    case 2: 
	                    	obj.NonvegMenu(); 
	                    	break;
                    case 3: 
	                    	obj.DessertsMenu();
	                    	break;
                    case 4: 
	                    	obj.BeveragesMenu(); 
	                    	break;
                    case 0: return;
                    default: 
                    		System.out.println(Colors.RED + "Invalid Choice." + Colors.RESET); 
                    		MenuList(obj);
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println(Colors.RED + "Enter only numbers!" + Colors.RESET);
                sc.nextLine();
                MenuList(obj);
            }

            System.out.println(Colors.YELLOW + "If You Want One More Item." + Colors.RESET);
            MenuList(obj);
        }

        
        void VegMenu() 
        {
        	System.out.println();
            System.out.println(Colors.BLUE+"========== Veg Menu =========="+ Colors.RESET);
            String[] vegmenu= {"Paneer Butter Masala ","Veg Palav ","Tomato Rice ","Kaju Rice ","Zeera Rice ","Veg Biryani ","Mushroom Curry ","Aloo Gobi "};
            double[] vegprice= {200.00,250.00,150.00,300.00,350.00,280.00,220.00,180.00};
            for(int i=0;i<8;i++)
            {
            	System.out.println(i+1+". "+vegmenu[i]+"= "+vegprice[i]);
            }
            System.out.println("0. Exit");
            try
            {
                System.out.print("Pick Item: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: Order(vegprice[0]);
                    		cart(vegmenu[0],vegprice[0]);
                    		break;
                    		
                    case 2: Order(vegprice[1]); 
                    		cart(vegmenu[1],vegprice[1]);
                    		break;
                    		
                    case 3: Order(vegprice[2]);
                    		cart(vegmenu[2],vegprice[2]);
                    		break;
                    		
                    case 4: Order(vegprice[3]);
                    		cart(vegmenu[3],vegprice[3]);
                    		break;
                    		
                    case 5: Order(vegprice[4]);
                    		cart(vegmenu[4],vegprice[4]);
                    		break;
                    		
                    case 6: Order(vegprice[5]);
                    		cart(vegmenu[5],vegprice[5]);
                    		break;
                    		
                    case 7: Order(vegprice[6]); 
                    		cart(vegmenu[6],vegprice[6]);
                    		break;
                    		
                    case 8: Order(vegprice[7]); 
                    		cart(vegmenu[7],vegprice[7]);
                    		break;
                    		
                    case 0: return;
                    
                    default: System.err.println("Invalid Choice.");
                    		VegMenu();
                }
            } 
            catch (InputMismatchException e) 
            {
                System.err.println("Enter only numbers!");
                sc.nextLine();
                VegMenu();
            }
        }

        void NonvegMenu() 
        {
        	System.out.println();
            System.out.println(Colors.BLUE+"========== Nonveg Menu =========="+ Colors.RESET);
            String[] nonvegmenu= {"Bongu Biryani","Hyd Biryani","Fish Curry","Mutton Biryani","Natu-Kodi Biryani","Chicken 65","Prawns Curry","Egg Curry"};
            double[] nonvegprice= {400.00,300.00,250.00,600.00,350.00,280.00,450.00,200.00};
            for(int i=0;i<8;i++)
            {
            	System.out.println(i+1+". "+nonvegmenu[i]+"= "+nonvegprice[i]);
            }
            System.out.println("0. Exit");

            try {
                System.out.print("Pick Item: ");
                int choice = sc.nextInt();
                switch (choice) 
                {
                    case 1: Order(nonvegprice[0]); 
                    		cart(nonvegmenu[0],nonvegprice[0]);
                    		break;
                    		
                    case 2: Order(nonvegprice[1]);
                    		cart(nonvegmenu[1],nonvegprice[1]);
                    		break;
                    		
                    case 3: Order(nonvegprice[2]); 
                    		cart(nonvegmenu[2],nonvegprice[2]);
                    		break;
                    		
                    case 4: Order(nonvegprice[3]);
                    		cart(nonvegmenu[3],nonvegprice[3]);
                    		break;
                    		
                    case 5: Order(nonvegprice[4]);
                    		cart(nonvegmenu[4],nonvegprice[4]);
                    		break;
                    		
                    case 6: Order(nonvegprice[5]); 
                    		cart(nonvegmenu[5],nonvegprice[5]);
                    		break;
                    		
                    case 7: Order(nonvegprice[6]); 
                    		cart(nonvegmenu[6],nonvegprice[6]);
                    		break;
                    		
                    case 8: Order(nonvegprice[7]); 
                    		cart(nonvegmenu[7],nonvegprice[7]);
                    		break;
                    		
                    case 0: return;
                    
                    default: System.err.println("Invalid Choice.");
                    		NonvegMenu();
                }
            } 
            catch (InputMismatchException e) 
            {
                System.err.println("Enter only numbers!");
                sc.nextLine();
                NonvegMenu();
            }
        }

        void DessertsMenu() 
        {
        	System.out.println();
            System.out.println(Colors.BLUE+"========== Desserts Menu =========="+ Colors.RESET);
           
            String[] dessertsmenu= {"Vanilla Ice Cream","Sweet Pan","Amul Ice Cream","Butterscotch","Special Kheer","Gulab Jamun","Rasmalai","Chocolate Brownie"};
            double[] dessertsprice= {100.00,80.00,150.00,200.00,180.00,120.00,160.00,250.00};
            
            for(int i=0;i<8;i++)
            {
            	System.out.println(i+1+". "+dessertsmenu[i]+"= "+dessertsprice[i]);
            }
            System.out.println("0. Exit");

            try {
                System.out.print("Pick Item: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: Order(dessertsprice[0]); 
                    		cart(dessertsmenu[0],dessertsprice[0]);
                    		break;
                    		
                    case 2: Order(dessertsprice[1]);
                    		cart(dessertsmenu[1],dessertsprice[1]);
                    		break;
                    		
                    case 3: Order(dessertsprice[2]); 
                    		cart(dessertsmenu[2],dessertsprice[2]);
                    		break;
                    		
                    case 4: Order(dessertsprice[3]);
                    		cart(dessertsmenu[3],dessertsprice[3]);
                    		break;
                    		
                    case 5: Order(dessertsprice[4]); 
                    		cart(dessertsmenu[4],dessertsprice[4]);
                    		break;
                    		
                    case 6: Order(dessertsprice[5]); 
                    		cart(dessertsmenu[5],dessertsprice[5]);
                    		break;
                    		
                    case 7: Order(dessertsprice[6]); 
                    		cart(dessertsmenu[6],dessertsprice[6]);
                    		break;
                    		
                    case 8: Order(dessertsprice[7]); 
                    		cart(dessertsmenu[7],dessertsprice[7]);
                    		break;
                    		
                    case 0: return;
                    		
                    default: System.err.println("Invalid Choice.");
                    		DessertsMenu();
                }
            } 
            catch (InputMismatchException e)
            {
                System.err.println("Enter only numbers!");
                sc.nextLine();
                DessertsMenu();
            }
        }

        void BeveragesMenu() 
        {
        	System.out.println();
            System.out.println(Colors.BLUE+"========== Beverages Menu =========="+ Colors.RESET);
            System.out.println("1. Non-Alcoholic");
            System.out.println("2. Alcoholic");
            System.out.println("0. Exit");

            try {
                System.out.print("Pick Type: ");
                int choice = sc.nextInt();
                switch (choice) 
                {
                    case 1:	System.out.println();
	                        System.out.println(Colors.BLUE+"\n======= Non-Alcoholic Beverages ======="+ Colors.RESET);
	                        
	                        String[] nonalcoholicmenu= {"Coke","Lime Soda","Coffee","Tea","Mango Juice","Cold Coffee","Milkshake","Mineral Water"};
	                        double[] nonalcoholicprice= {50.00,80.00,60.00,40.00,70.00,120.00,150.00,20.00};
	                        
	                        for(int i=0;i<8;i++)
	                        {
	                        	System.out.println(i+1+". "+nonalcoholicmenu[i]+"= "+nonalcoholicprice[i]);
	                        }
	                        System.out.println("0. Exit");
	                        
	                        System.out.print("Pick Item: ");
	                        int non = sc.nextInt();
	                        switch (non) 
	                        {
	                            case 1: Order(nonalcoholicprice[0]);
	                            		cart(nonalcoholicmenu[0],nonalcoholicprice[0]);
	                                   	break;
	                                   	
	                            case 2: Order(nonalcoholicprice[1]); 
	                            		cart(nonalcoholicmenu[1],nonalcoholicprice[1]);
	                            		break;
	                            		
	                            case 3: Order(nonalcoholicprice[2]); 
	                            		cart(nonalcoholicmenu[2],nonalcoholicprice[2]);
	                            		break;
	                            		
	                            case 4: Order(nonalcoholicprice[3]); 
	                            		cart(nonalcoholicmenu[3],nonalcoholicprice[3]);
	                            		break;
	                            		
	                            case 5: Order(nonalcoholicprice[4]);
	                            		cart(nonalcoholicmenu[4],nonalcoholicprice[4]);
	                            		break;
	                            		
	                            case 6: Order(nonalcoholicprice[5]); 
	                            		cart(nonalcoholicmenu[5],nonalcoholicprice[5]);
	                            		break;
	                            		
	                            case 7: Order(nonalcoholicprice[6]);
	                            		cart(nonalcoholicmenu[6],nonalcoholicprice[6]);
	                            		break;
	                            		
	                            case 8: Order(nonalcoholicprice[7]);
	                            		cart(nonalcoholicmenu[7],nonalcoholicprice[7]);
	                            		break;
	                            		
	                            case 0: return;
	                            		
	                            default: System.err.println("Invalid Choice.");
	                            		 BeveragesMenu();	
	                        }
	                        break;
	                        
                    case 2:	System.out.println();
	                        System.out.println(Colors.CYAN+"======= Alcoholic Beverages ======="+ Colors.RESET);
	                     
	                        String[] alcoholicmenu= {"Beer","Whiskey","Vodka","Rum","Brandy","Wine","Champagne","Teqila"};
	                        double[] alcoholicprice= {150.00,250.00,220.00,180.00,200.00,300.00,500.00,400.00};
	                        
	                        for(int i=0;i<8;i++)
	                        {
	                        	System.out.println(i+1+". "+alcoholicmenu[i]+"= "+alcoholicprice[i]);
	                        }
	                        System.out.println("0. Exit");
	                        System.out.print("Pick Item: ");
	                        int al = sc.nextInt();
	                        switch (al) 
	                        {
	                            case 1: Order(alcoholicprice[0]);
	                            		cart(alcoholicmenu[0],alcoholicprice[0]);
	                                    break;
	                                    
	                            case 2: Order(alcoholicprice[1]);
	                            		cart(alcoholicmenu[1],alcoholicprice[1]);
	                            		break;
	                            		
	                            case 3: Order(alcoholicprice[2]);
	                            		cart(alcoholicmenu[2],alcoholicprice[2]);
	                            		break;
	                            		
	                            case 4: Order(alcoholicprice[3]); 
                        				cart(alcoholicmenu[3],alcoholicprice[3]);
	                            		break;
	                            		
	                            case 5: Order(alcoholicprice[4]); 
                        				cart(alcoholicmenu[4],alcoholicprice[4]);
	                            		break;
	                            		
	                            case 6: Order(alcoholicprice[5]);
                        				cart(alcoholicmenu[5],alcoholicprice[5]);
	                            		break;
	                            		
	                            case 7: Order(alcoholicprice[6]);
                        				cart(alcoholicmenu[6],alcoholicprice[6]);
	                            		break;
	                            			
	                            case 8: Order(alcoholicprice[7]);
                        				cart(alcoholicmenu[7],alcoholicprice[7]);
	                            		break;
	                            		
	                            case 0: return;
	                            		
	                            default: System.err.println("Invalid Choice.");
	                            		 BeveragesMenu();
	                        }
	                        break;
                    case 0: return;
                    
                    default:
	                        System.err.println("Invalid Beverage Type.");
	                        BeveragesMenu();
                }
            } 
            catch (InputMismatchException e) 
            {
                System.err.println("Enter only numbers!");
                sc.nextLine();
                BeveragesMenu();
            }
        }
      
        void Order(double Price)
        {
            try 
            {
            	System.out.println(Colors.PURPLE+"If You want to Cancel this item Simple place '0' in Quantity"+Colors.RESET);
                System.out.print(Colors.CYAN + "Quantity : " + Colors.RESET);
                int Quantity = sc.nextInt();
                if(Quantity>=0)
                {
	                itemQuantity(Quantity);
	                TotalPrice += Price * Quantity;
	                uptoBill(TotalPrice);
                }
                else
                {
                	System.out.println(Colors.RED+"Mention Quantity in Positive number"+Colors.RESET);
                	Order(Price);
                }
                
            } 
            catch (InputMismatchException e) 
            {
                System.out.println(Colors.RED + "Enter only numbers!" + Colors.RESET);
                sc.nextLine();
                Order(Price);
            }
        }
        static int n=0,q=0; 
        
	    static String[] cartitems= new String[20];
	    
	    static double[] itemprice=new double[20];
	    
	    static int[] quantity=new int[20]; 
	    
        static void cart(String item,double price)
        {
        	cartitems[n]=item;
        	itemprice[n++]=price;
        }
        static void itemQuantity(int a)
        {
        	quantity[q++]=a;
        }
        static void uptoBill(double TotalPrice) 
        {
            System.out.println(Colors.PURPLE + " Your Total Bill : ₹" + TotalPrice + Colors.RESET);
        }
        void GenerateBill() 
        {
        	double taxRate = 0.08;
    		double taxAmount = TotalPrice * taxRate;
    		double finalCost = TotalPrice + taxAmount;

    		System.out.println(Colors.RED+"=====You Ordered Items====="+Colors.RESET);
    		for(int k=0;k<30;k++)
    			System.out.print("--");
    		System.out.println();
    		System.out.println(Colors.YELLOW+"S.NO  Item Name                        Price    Q   Total"+Colors.RESET);
    		for(int k=0;k<30;k++)
    			System.out.print("--");
    		System.out.println();
    		for(int i=0;i<20;i++)
			{
				if(itemprice[i]!=0)
				{
					System.out.print(" "+(i+1)+".   "+cartitems[i]);
					for(int j=30-cartitems[i].length();j>=0;j--)
					{
						System.out.print(" ");
					}
					System.out.println(" = "+"₹"+itemprice[i]+" X "+quantity[i]+" = ₹"+(itemprice[i]*quantity[i]));
				}
			}
    		for(int k=0;k<30;k++)
    			System.out.print("--");
    		System.out.println();
    		System.out.println(Colors.BLUE+"======= Bill Summary ======="+ Colors.RESET);
    		System.out.printf("Total Cost (before tax): ₹%.2f\n", TotalPrice);
    		System.out.printf("Tax (8%%): ₹%.2f\n", taxAmount);
    		System.out.printf("Final Amount to Pay: ₹%.2f\n", finalCost);
        }
        
        static void PaymentType() throws InterruptedException 
        {
        	System.out.println();
            System.out.println(Colors.BLUE + "========== PAYMENT ==========" + Colors.RESET);
            System.out.println("1. UPI ");
            System.out.println("2. CASH ");
            System.out.print(Colors.CYAN + "Choose Option : " + Colors.RESET);
            try 
            {
                int option = sc.nextInt();
                switch (option) {
                    case 1:
	                        System.out.print("Enter Your Pin number : ");
	                        String pin = sc.next();
	   
	                        if (pin.length() == 4 || pin.length() == 6) 
	                        {
	                            System.out.print(Colors.YELLOW + "Processing Payment" + Colors.RESET);
	                            for (int i = 0; i < 5; i++) 
	                            {
	                                Thread.sleep(600);
	                                System.out.print(".");
	                            }
	                            System.out.println();
	                            System.out.println(Colors.GREEN + "Payment Successful!" + Colors.RESET);
	                        } 
	                        else 
	                        {
	                            System.out.println(Colors.RED + "Invalid Pin." + Colors.RESET);
	                            PaymentType();
	                        }
	                        break;
                    case 2:
	                        System.out.println(Colors.GREEN + "Cash Payment Successful..." + Colors.RESET);
	                        break;
                    default:
	                        System.out.println(Colors.RED + "Invalid Choice." + Colors.RESET);
	                        PaymentType();
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println(Colors.RED + "Enter only numbers!" + Colors.RESET);
                sc.nextLine();
                PaymentType();
            }
        }
        

        
  
        public static void main(String[] args) throws InterruptedException 
        {
            String msg = "===== WELCOME TO NA POTTA NA ISTAM =====";
            
            for (int i = 0; i < msg.length(); i++) 
            {
                char c = msg.charAt(i);
                System.out.print(Colors.CYAN + c + Colors.RESET);
                Thread.sleep(100);
            }
            System.out.println();
            
            System.out.println();
            System.out.println(Colors.PURPLE +"##### Please Register ##### "+Colors.RESET);
            System.out.println();
            
            System.out.println(Colors.YELLOW + "Please Follow These Constraints While You Set Your Username" + Colors.RESET);
            System.out.println("=> User name should be at least 10 characters.");
            System.out.println("=> Compulsory User name ends with '@gmail.com'");

            String un = username();
            System.out.println();

            System.out.println(Colors.YELLOW + "Please Follow These Password Constraints" + Colors.RESET);
            System.out.println("=> At least 8 characters.");
            System.out.println("=> At least 2 digits.");
            System.out.println("=> First letter Uppercase.");

            String pw = password(); 
            
            System.out.println();
            A obj = new A(un, pw);
             
            System.out.println();
            obj.Login(obj);
            
            Restaurant  rest = obj;
            System.out.println();
    		MenuList(rest);
            
            if(A.TotalPrice==0)
    		{
    			System.out.println();
    			System.out.println(Colors.CYAN +"Please Order atleast One Item"+ Colors.RESET);
    			System.out.println();
    			System.out.println(Colors.CYAN +"You Don't Want To Order Anything Press '0' Again to Exit From Menu"+ Colors.RESET);
    			MenuList(rest);
    		}
    		if(A.TotalPrice>0)
    		{
    			System.out.println();
    			obj.GenerateBill();
    			
    			PaymentType();
    		}
    		System.out.println();
    		System.out.println(Colors.PURPLE +"-----Thank You For Visiting-----"+ Colors.RESET);
    		
    		System.out.println(Colors.PURPLE +"-----Have a Nice Day Visit Again-----"+ Colors.RESET);
        }

    }