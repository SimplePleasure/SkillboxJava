public class HelloWorldAnonymousClasses {




    String str = "helloworld ->  TEST";


    interface HelloWorld {

        public void greet();
        public void greetSomeone(String someone);
    }

    public void sayHello() {


        String str = "helloworld-> sayhello() -> uuuuu";

        class EnglishGreeting implements HelloWorld {

            String str = "helloworld-> sayhello() -> englishGr -> uuuuu";


            String name = "world";
            public void greet() {
                greetSomeone("world");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("English lqang/ Hello " + name);
                System.out.println(HelloWorldAnonymousClasses.this.str +"\n" +
                        this.str);
            }
        }
        HelloWorld englishGreeting = new EnglishGreeting();







        HelloWorld frenchGreeting = new HelloWorld() {



            String str = "bgdbh";
            String name = "tout le monde";
            public void greet() {
                greetSomeone("tout le monde");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);
                System.out.println(HelloWorldAnonymousClasses.this.str);
                System.out.println(this.str);
            }
        };

        HelloWorld spanishGreeting = new HelloWorld() {
            String name = "mundo";
            public void greet() {
                greetSomeone("mundo");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hola, " + name);
            }
        };
        //frenchGreeting.greet();
        englishGreeting.greet();
        //frenchGreeting.greetSomeone("Fred");
        //spanishGreeting.greet();
    }

    public static void main(String[] args) {




        HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();
        myApp.sayHello();
    }
}