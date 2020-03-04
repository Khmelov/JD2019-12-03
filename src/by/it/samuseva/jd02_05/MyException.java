package by.it.samuseva.jd02_05;

class MyException extends Exception {
     MyException() {
    }

    MyException(String message) {
        super(Message.EXCEPTION + message);
    }

}
