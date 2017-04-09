public final class SingletonJava {
    private SingletonJava() {
        INSTANCE = (SingletonJava) this;
    }

    public static SingletonJava INSTANCE;

    static {
        new SingletonJava();
    }
}