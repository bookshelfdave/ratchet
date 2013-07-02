package com.metadave.ratchet;

import jline.ANSIBuffer;
import jline.console.ConsoleReader;

public class RatchetConsole {

    public RatchetConsole() {

    }

    public void run() throws Exception {
        RatchetContext ctx = new RatchetContext();

        ConsoleReader reader = new ConsoleReader();
        reader.setBellEnabled(false);
        reader.setExpandEvents(false); // TODO: look into this
        // TODO: Pasting in text with tabs prints out a ton of completions
        //reader.addCompleter(new jline.console.completer.StringsCompleter(keywords));


        ANSIBuffer buf = new ANSIBuffer();
        buf.blue("Welcome to Ratchet v1.0\n");
        buf.blue("(c) 2013 Dave Parfitt\n");
        System.out.println(buf.toString());


        StringBuffer lines = new StringBuffer();
        ANSIBuffer ansiprompt = new ANSIBuffer();
        ansiprompt.setAnsiEnabled(true);
        ansiprompt.green("> ");
        boolean inHereDoc = false;
        String line;
        boolean nextLinePrompt = false;
        String prompt = ansiprompt.toString();

        while ((line = reader.readLine(nextLinePrompt ? "" : prompt)) != null) {


            if (!line.trim().endsWith(";")) {
                nextLinePrompt = true;
                lines.append(line);
                lines.append("\n");
            } else if (line.trim().endsWith(";")) {
                lines.append(line);
                String input = lines.toString();
                nextLinePrompt = false;
                //processInput(input, walkers, ctx);
                //processOutput(ctx, out, !commandLine.hasOption("nocolor"));
                lines = new StringBuffer();
            }
        }
    }
    public static void main(String main[]) throws Exception {
        new RatchetConsole().run();
    }
}
