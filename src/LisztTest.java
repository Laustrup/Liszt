import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

class LisztTest {

    private Liszt<Object> liszt;

    @BeforeEach
    public void beforeEach() {
        liszt = new Liszt<>();
    }

    @ParameterizedTest
    @CsvSource(value = {"1", "2", "3"}, delimiter = '|')
    public void addArrayTest(int indexs) {
        //Arrange
        Object[] inputs = new Object[indexs];
        for (int i = 0;i < indexs;i++) {
            inputs[i] = i;
        }

        //Act
        Object[] act = liszt.addArray(inputs);

        //Parse
        int[] expecteds = new int[indexs];
        int[] actuals = new int[act.length];
        for (int i = 0;i < indexs;i++) {
            expecteds[i] = i;
        }
        for (int i = 0;i < act.length;i++) {
            actuals[i] = i;
        }

        //Assert
        for (int i = 0; i < indexs;i++) {
            assertEquals(inputs[i],actuals[i]);
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"1|1", "2|1", "2|2", "3|1", "3|2", "3|3"}, delimiter = '|')
    public void indexOfTest(int indexs, int expected) {
        //Arrange
        Object[] objects = new Object[indexs];
        for (int i = 0;i < indexs;i++) {
            objects[i] = i;
        }

        //Act
        liszt = new Liszt(true,objects);
        int actual = liszt.indexOf(expected);

        //Assert
        assertEquals(expected,actual);
    }
}