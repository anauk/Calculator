package peoples;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class CalculatorFilter implements Filter {

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
