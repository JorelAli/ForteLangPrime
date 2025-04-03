import java.lang.reflect.Method;

public class TestDynamicAddOne {
    public static void main(String[] args) throws Exception {
        // Generate the bytecode
        byte[] bytecode = AddOneFunctionGenerator.generateAddOneClass();

        // Create custom classloader with access to Thunk, MemoThunk, etc.
        DynamicClassLoader loader = new DynamicClassLoader(TestDynamicAddOne.class.getClassLoader());

        // Define AddOneFunction class in that loader
        Class<?> addOneClass = loader.defineClass("AddOneFunction", bytecode);

        // Load Thunk from our local codebase (needed for .getMethod to work)
        Class<?> thunkClass = loader.loadClass("Thunk");

        // Reflectively get method
        Method apply = addOneClass.getMethod("apply", thunkClass);

        // Create a lazy thunk instance
        Thunk<Integer> lazyThree = new MemoThunk<>() {
            @Override
            protected Integer compute() {
                System.out.println("Evaluating lazyThree...");
                return 3;
            }
        };

        // Invoke dynamically loaded method
        int result = (int) apply.invoke(null, lazyThree);
        System.out.println("Result: " + result);
        System.out.println("Second call result: " + lazyThree.get());
    }
}