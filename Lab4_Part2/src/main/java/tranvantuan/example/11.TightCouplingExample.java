package tranvantuan.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class TightCouplingExample {

    private static final Logger LOGGER =
            Logger.getLogger(TightCouplingExample.class.getName());

    private static final String GENERATE_MSG = "Generating report...";

    private final Printer printer = new ConsolePrinter();

    static void main() {
        new TightCouplingExample().generate();
    }

    public void generate() {
        LOGGER.log(Level.INFO, GENERATE_MSG);

        Report report = new Report("Q4/2025", "Revenue up 12%, costs stable.");
        String rendered = report.render();

        printer.print(rendered);
        LOGGER.log(Level.INFO, "Report generation completed.");
    }

    interface Printer { void print(String content); }

    static class ConsolePrinter implements Printer {
        @Override public void print(String content) {
            LOGGER.log(Level.INFO, "Printing report:\n{0}", content);
        }
    }

    record Report(String title, String body) {
        String render() {
                return "=== REPORT ===\nTitle : " + title + "\nBody  : " + body + "\n";
            }
        }
}
