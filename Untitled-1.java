//To download this: press "raw" button -> save as Maman15Test.java.
//Place in same package where PolyNode and Polynom classes are located and run.

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Fully automated tests for maman 15 (OUI).
 * This class will test your PolyNode and Polynom classes and generate a
 * report about methods working properly and failures.
 * <p>
 * -------------------------------------------------------------
 * NOTE: THESE TESTS ARE WRITTEN BY A STUDENT, NOT BY A TEACHER!
 * RELY ON THE RESULTS AT YOUR OWN RISK!
 * OFFICIAL TESTS CAN BE FOUND ON FORUM.
 * --------------------------------------------------------------
 * <p>
 * If you want to update these tests on pastebin.com,
 * send an email to skorn.ilya AT gmail DOT com containing a copy of this file with your changes.
 * Your experience and issues: https://vk.cc/aiX8QX
 * <p>
 * Thanks for everyone who leaves a feedback! =)
 * <p>
 *
 * @author Ilya Skornyakov
 * @version 1.0
 * @since JDK 8
 * <p>
 * Updates: https://pastebin.com/FpLYnUnh
 */

public class Maman15Test {

    private static final boolean VERBOSE = false;
    private static final String VERSION = "1.0";
    private static final String UPDATES_LINK = "https://pastebin.com/FpLYnUnh";
    private static int errorsCurrent = 0;
    private static int errors = 0;
    private static Map<String, Map<String, Integer>> methodErrorsCount = new HashMap<>();
    private static Map<String, Map<String, ArrayList<String>>> methodErrorsMessages = new HashMap<>();
    private static Map<String, Integer> totalErrors = new HashMap<>();
    private static String cClass = "";
    private static String cMethod = "";
    private static final ClassTest polynomeTests = new ClassTest("Polynom", new Test[]{
            new Test("Constructor & toString()", () -> {
                PolyNode p_28x10 = new PolyNode(10, 2.8);
                PolyNode p_0x8 = new PolyNode(8, 0);
                PolyNode p__49x4 = new PolyNode(4, -4.9);
                PolyNode p_0x3 = new PolyNode(3, 0);
                PolyNode p_65x2 = new PolyNode(2, 6.5);
                PolyNode p__12 = new PolyNode(0, -12);

                p_28x10.setNext(p_0x8);
                p_0x8.setNext(p__49x4);
                p__49x4.setNext(p_0x3);
                p_0x3.setNext(p_65x2);
                p_65x2.setNext(p__12);

                Polynom pNom = new Polynom();
                assertEquals(pNom.toString(), "");
                pNom.setHead(p_28x10);

                Polynom pNom2 = new Polynom(p_28x10);

                assertEquals(pNom, pNom2);
                assertEquals(pNom.toString(), "2.8x^10-4.9x^4+6.5x^2-12.0");
                assertEquals(getPolynom("-15x^6+9x^2-6x+3").toString(), "-15.0x^6+9.0x^2-6.0x+3.0");
                PolyNode p21 = new PolyNode(10, 0);
                PolyNode p22 = new PolyNode(2, 2);

                p21.setNext(p22);

                assertEquals(new Polynom(p21).toString(), "2.0x^2");

                p22.setCoefficient(-2);

                assertEquals(new Polynom(p21).toString(), "-2.0x^2");

            }),
            new Test("getHead() & setHead()", () -> {
                PolyNode p_28x10 = new PolyNode(10, 2.8);
                PolyNode p_0x8 = new PolyNode(8, 0);
                PolyNode p__49x4 = new PolyNode(4, -4.9);
                PolyNode p_0x3 = new PolyNode(3, 0);
                PolyNode p_65x2 = new PolyNode(2, 6.5);
                PolyNode p__12 = new PolyNode(0, -12);

                p_28x10.setNext(p_0x8);
                p_0x8.setNext(p__49x4);
                p__49x4.setNext(p_0x3);
                p_0x3.setNext(p_65x2);
                p_65x2.setNext(p__12);

                Polynom pNom = new Polynom();

                pNom.setHead(p_28x10);

                //aliasing
                assertEquals(pNom.toString(), "2.8x^10-4.9x^4+6.5x^2-12.0");
                p_28x10.setCoefficient(3);
                assertEquals(pNom.toString(), "3.0x^10-4.9x^4+6.5x^2-12.0");
                PolyNode head = pNom.getHead();
                head.setCoefficient(4);
                assertEquals(pNom.toString(), "4.0x^10-4.9x^4+6.5x^2-12.0");

            }),
            new Test("addNode()", () -> {
                Polynom exp = getPolynom("5x^6+111x^5+3x^2+2x+1");

                PolyNode p2_0 = getPolynode("1");
                PolyNode p2_1 = getPolynode("2x");
                PolyNode p2_2_1 = getPolynode("2x^2");
                PolyNode p2_2_2 = getPolynode("x^2");
                PolyNode p2_5_1 = getPolynode("10x^5");
                PolyNode p2_5_2 = getPolynode("100x^5");
                PolyNode p2_5_3 = getPolynode("x^5");
                PolyNode p2_5_4 = getPolynode("0x^5");
                PolyNode p2_6 = getPolynode("5x^6");
                PolyNode p2_7 = getPolynode("5x^3");
                PolyNode p2__7 = getPolynode("-5x^3");

                Polynom act1 = new Polynom();
                act1.addNode(new PolyNode(p2_0));
                act1.addNode(new PolyNode(p2_5_1));
                act1.addNode(new PolyNode(p2__7));
                act1.addNode(new PolyNode(p2_5_2));
                act1.addNode(new PolyNode(p2_2_1));
                act1.addNode(new PolyNode(p2_6));
                act1.addNode(new PolyNode(p2_1));
                act1.addNode(new PolyNode(p2_7));
                act1.addNode(new PolyNode(p2_5_3));
                act1.addNode(new PolyNode(p2_2_2));

                assertEquals(act1.addNode(new PolyNode(p2_5_4)), exp, true);

                assertEquals(act1, exp, true);

                Polynom act2 = new Polynom(new PolyNode(p2_5_4));
                act2.addNode(new PolyNode(p2_2_2));
                act2.addNode(new PolyNode(p2_6));
                act2.addNode(new PolyNode(p2__7));
                act2.addNode(new PolyNode(p2_0));
                act2.addNode(new PolyNode(p2_7));
                act2.addNode(new PolyNode(p2_5_2));
                act2.addNode(new PolyNode(p2_1));
                act2.addNode(new PolyNode(p2_5_1));
                act2.addNode(new PolyNode(p2_5_3));
                act2.addNode(new PolyNode(p2_2_1));

                assertEquals(act2, exp, true);

                PolyNode[] polyNodes = new PolyNode[]{p2_0, p2_1, p2_2_1, p2_2_2, p2_5_1, p2_5_2, p2_5_3, p2_5_4, p2_6, p2__7, p2_7};

                for (int i = 0; i < 1000; i++) {
                    Polynom actRandom = new Polynom();
                    int ec = errorsCurrent;
                    Random random = ThreadLocalRandom.current();
                    //shuffle
                    for (int j = 0; j < polyNodes.length - 1; j++) {
                        int index = random.nextInt(j + 1);
                        PolyNode a = polyNodes[index];
                        polyNodes[index] = polyNodes[j];
                        polyNodes[j] = a;
                    }

                    StringBuilder order = new StringBuilder();
                    for (int j = 0; j < polyNodes.length; j++) {
                        if (j != 0)
                            order.append(" -> ");
                        order.append(String.format("(%s) [coef: %.1f, pow: %d]", polyNodes[j].toString(), polyNodes[j].getCoefficient(), polyNodes[j].getPower()));
                        actRandom.addNode(new PolyNode(polyNodes[j]));
                    }

                    assertEquals(actRandom.toString(), "5.0x^6+111.0x^5+3.0x^2+2.0x+1.0", "COMPARED AS A STRING. Added in order: " + order.toString());

                    if (ec != errorsCurrent)
                        break;
                }


                //aliasing
                p2_0 = getPolynode("10");
                p2_2_1 = getPolynode("10x");
                p2_6 = getPolynode("10x^2");

                assertEquals(act2, exp, true);
                assertEquals(act1, exp, true);

                //toString
                PolyNode p3_1 = getPolynode("3x^2");
                PolyNode p3_2 = getPolynode("3x^3");
                PolyNode p3_3 = getPolynode("-3x^3");

                Polynom p3 = new Polynom(p3_1);
                p3.addNode(p3_2);
                p3.addNode(p3_3);

                assertEquals(p3.toString(), "3.0x^2");
            }),
            new Test("multByScalar()", () -> {
                Polynom pm = getPolynom("-5x^6+0x^5+3x^2-2x+1");
                Polynom pm2 = getPolynom("-15x^6+9x^2-6x+3");

                assertEquals(pm.multByScalar(3), pm2, true);
                assertEquals(pm, pm2, true);

                Polynom pm3 = getPolynom("30x^6-18x^2+12x-6");

                pm2.multByScalar(-2);

                assertEquals(pm2, pm3, true);
                assertEquals(pm3.multByScalar(0).toString(), "");
            }),
            new Test("multPol()", () -> {
                assertEquals(getPolynom("-5x^6+0x^5+3x^2-2x+1")
                                .multPol(getPolynom("-15x^6+9x^2-6x+3")),
                        getPolynom("75x^12-90x^8+60x^7-30x^6+27x^4-36x^3+30x^2-12x+3"), true);

                assertEquals(getPolynom("-5x^6+0x^5+3x^2-2x+1")
                                .multPol(getPolynom("")),
                        getPolynom("-5x^6+0x^5+3x^2-2x+1"), true);


                assertEquals(getPolynom("")
                                .multPol(getPolynom("-5x^6+3x^2-2x+1")),
                        getPolynom(""), true);

                assertEquals(new Polynom()
                                .multPol(getPolynom("")),
                        new Polynom(), true);


                assertEquals(getPolynom("8x^5-2x^2+5x-8")
                                .multPol(null),
                        getPolynom("8x^5-2x^2+5x-8"));

            }),
            new Test("addPol()", () -> {
                assertEquals(getPolynom("x^2+6x^1-15")
                                .addPol(getPolynom("10x^6+8x^5-3x^2-1x^1+7")),
                        getPolynom("10x^6+8x^5-2x^2+5x-8"));

                assertEquals(getPolynom("8x^5-3x^2-1x^1+7")
                                .addPol(getPolynom("x^2+6x^1-15")),
                        getPolynom("8x^5-2x^2+5x-8"));

                assertEquals(getPolynom("10x^7+8x^5-3x^2-1x^1+7")
                                .addPol(getPolynom("x^8+6x^1-15")),
                        getPolynom("x^8+10x^7+8x^5-3x^2+5x-8"));

                assertEquals(getPolynom("8x^6-3x^4-1x^2+7")
                                .addPol(getPolynom("x^5+6x")),
                        getPolynom("8x^6+x^5-3x^4-1x^2+6x+7"));

                assertEquals(getPolynom("8x^5")
                                .addPol(getPolynom("3x^2-1x+2")),
                        getPolynom("8x^5+3x^2-1x+2"));

                assertEquals(getPolynom("3x^2")
                                .addPol(getPolynom("3x^2-1x+2")),
                        getPolynom("6x^2-1x+2"));

                assertEquals(new Polynom()
                                .addPol(getPolynom("8x^5-2x^2+5x-8")),
                        getPolynom("8x^5-2x^2+5x-8"));

                assertEquals(getPolynom("8x^5-2x^2+5x-8")
                                .addPol(new Polynom()),
                        getPolynom("8x^5-2x^2+5x-8"));

                assertEquals(getPolynom("8x^5-2x^2+5x-8")
                                .addPol(null),
                        getPolynom("8x^5-2x^2+5x-8"));

                assertEquals(new Polynom().addPol(new Polynom()), new Polynom());

                assertEquals(getPolynom("10x^7+8x^5-3.2x^2-x+7")
                                .addPol(getPolynom("x^8+3.2x^2+6x-15")),
                        getPolynom("x^8+10x^7+8x^5+5x-8"), true);

                assertEquals(getPolynom("10x^7+8x^5-3.2x^2-x+7")
                        .addPol(getPolynom("-10x^7-8x^5+3.2x^2+x-7")), new Polynom(), true);

                //immutability
                Polynom pm1_1 = getPolynom("x^2+6x^1-15");
                Polynom pm1_2 = getPolynom("10x^6+8x^5-3x^2-1x^1+7");
                Polynom pm1_2_2 = getPolynom("10x^6+8x^5-3x^2-1x^1+7");
                pm1_1.addPol(pm1_2);
                assertEquals(pm1_2, pm1_2_2);

                //remove and sum
                Polynom pm2_1 = getPolynom("x^2+6x^1-15");
                Polynom pm2_2 = getPolynom("-6x^1");
                Polynom pm2_3 = getPolynom("2x^1");
                Polynom pm2_4 = getPolynom("x^2+2x^1-15");
                assertEquals(pm2_1.addPol(pm2_2).addPol(pm2_3), pm2_4, true);

                assertEquals(pm2_4.addPol(new Polynom(new PolyNode(2, -1))).toString(), "2.0x-15.0");
            }),
            new Test("differential()", () -> {
                Polynom pm = getPolynom("8x^3-3x^2-x+7");
                Polynom pm2 = getPolynom("24x^2-6x-1");
                pm.differential();
                assertEquals(pm, pm2, true);
            }),

    });
    private static final ClassTest polyNodeTests = new ClassTest("PolyNode", new Test[]{
            new Test("Constructor", () -> {
                PolyNode p1 = new PolyNode(2, 6.5);
                PolyNode p2 = new PolyNode(p1);
                assertEquals(p1, p2);
                PolyNode p3 = new PolyNode(3, 5, p1);
                p2.setNext(p3);
                p3.setPower(3);
                p3.getNext().setCoefficient(6);
                assertEquals(p2.getNext(), p3);

                PolyNode p4 = new PolyNode(-1, 10);
                PolyNode p5 = new PolyNode(-1, 10, p1);
                PolyNode pDef = new PolyNode(0, 0);
                PolyNode pDef2 = new PolyNode(0, 0, p1);
                assertEquals(p4, pDef);
                assertEquals(p5, pDef2);

                PolyNode p6 = new PolyNode(1, 10);
                PolyNode p7 = new PolyNode(2, 10);
                PolyNode p8 = new PolyNode(3, 10);

                p7.setNext(p6);
                p8.setNext(p7);

                PolyNode pCopy = new PolyNode(p8);

                assertEquals(p8, pCopy);

                pCopy.setCoefficient(20);
                pCopy.getNext().setCoefficient(20);
                pCopy.getNext().getNext().setCoefficient(20);

                assertFalse(pCopy.getCoefficient() == p8.getCoefficient());
                assertTrue(pCopy.getNext().getCoefficient() == p7.getCoefficient());
                assertTrue(pCopy.getNext().getNext().getCoefficient() == p6.getCoefficient());
            }),
            new Test("toString()", () -> {
                PolyNode p_1 = new PolyNode(2, 6.5);
                assertEquals(p_1.toString(), "6.5x^2");
                PolyNode p_2 = new PolyNode(2, -6.5);
                assertEquals(p_2.toString(), "-6.5x^2");
                PolyNode p_3 = new PolyNode(0, 6.5);
                assertEquals(p_3.toString(), "6.5");
                PolyNode p_4 = new PolyNode(2, 1);
                assertEquals(p_4.toString(), "x^2");
                PolyNode p_5 = new PolyNode(2, -1);
                assertEquals(p_5.toString(), "-x^2");
                PolyNode p_6 = new PolyNode(0, 1);
                assertEquals(p_6.toString(), "1.0");
                PolyNode p_7 = new PolyNode(0, -1);
                assertEquals(p_7.toString(), "-1.0");
                PolyNode p_8 = new PolyNode(1, 3);
                assertEquals(p_8.toString(), "3.0x");
                PolyNode p_9 = new PolyNode(1, -1);
                assertEquals(p_9.toString(), "-x");

                PolyNode p_10 = new PolyNode(1, 0);
                assertEquals(p_10.toString(), "");
                PolyNode p_11 = new PolyNode(2, 0);
                assertEquals(p_11.toString(), "");
                PolyNode p_12 = new PolyNode(0, 0);
                assertEquals(p_12.toString(), "");


            }),
            new Test("Getters & Setters", () -> {
                PolyNode p1 = new PolyNode(2, 10);
                PolyNode p2 = new PolyNode(3, 10);

                p2.setNext(p1);

                p1.setPower(0);

                assertEquals(p1.getPower(), 0);

                p1.setPower(1);
                p1.setPower(-1);

                assertEquals(p1.getPower(), 1);

                p2.getNext().setCoefficient(20);

                assertEquals((int) p1.getCoefficient(), 20);
            }),
    });

    public static void main(String[] args) {
        doTests(polyNodeTests);
        doTests(polynomeTests);
        report();
    }

    private static int doTests(ClassTest tests) {
        String className = tests.getClassName();
        cClass = className;
        if (VERBOSE)
            vPrint("=========================================\n" +
                    "\t\t\tTesting now: %s\n" +
                    "-----------------------------------------\n", className);
        else
            vPrint("\nTesting now: %s\n", className.toUpperCase());

        methodErrorsCount.put(className, new HashMap<>());
        methodErrorsMessages.put(className, new HashMap<>());
        errorsCurrent = 0;
        for (Test t : tests.getTests()) {
            cMethod = t.getName();

            if (VERBOSE) vPrint("----= %s =----\n", t.getName());

            int c = errorsCurrent;

            try {
                t.getTest().run();
            } catch (Exception e) {
                StringWriter writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                failedAssert("Exception: " + writer.toString());
            }

            c = errorsCurrent - c;
            reportMethodErrors(t.getName(), c);
            cMethod = "";
            methodErrorsCount.get(className).put(t.getName(), c);
        }
        totalErrors.put(className, errorsCurrent);
        cClass = "";
        int e = errorsCurrent;
        errorsCurrent = 0;
        return e;
    }

    private static Polynom getPolynom(String polynom) {
        if (polynom.isEmpty())
            return new Polynom();

        polynom = polynom.replace("-", "-_").replace(" ", "");
        String[] nodes = polynom.split("[\\+\\-]");
        int h = nodes[0].isEmpty() ? 1 : 0;
        PolyNode head = getPolynode(nodes[h].replace("_", "-"));

        PolyNode c = head;

        for (int i = h + 1; i < nodes.length; i++) {
            c.setNext(getPolynode(nodes[i].replace("_", "-")));
            c = c.getNext();
        }

        return new Polynom(head);
    }

    private static PolyNode getPolynode(String polynode) {
        if (polynode.equals("x"))
            return new PolyNode(1, 1);
        if (polynode.equals("-x"))
            return new PolyNode(1, -1);

        if (!polynode.contains("x")) {
            double c = Double.parseDouble(polynode);
            return new PolyNode(0, c);
        }
        if (polynode.contains("x^")) {
            String[] s = polynode.split("x\\^");
            s[0] = s[0].replace("+", "");

            double c = 1;
            if (!polynode.startsWith("x"))
                c = Double.parseDouble(s[0]);

            int p = Integer.parseInt(s[1]);
            return new PolyNode(p, c);
        } else {
            double c = Double.parseDouble(polynode.substring(0, polynode.indexOf("x")));
            return new PolyNode(1, c);
        }
    }


    private static void assertEquals(String actual, String expected) {
        assertEquals(actual, expected, "");
    }

    private static void assertEquals(String actual, String expected, String message) {
        if (actual.equals(expected))
            succeedAssert(String.format("%s and %s are equal", actual, expected));
        else {
            StringBuilder msg = new StringBuilder("Difference");
            if (!message.isEmpty())
                msg.append(" (" + message + ")");
            msg.append(":\n");

            actual = actual.replace("\n", "\\n").replace("\t", "\\t");
            expected = expected.replace("\n", "\\n").replace("\t", "\\t");

            msg.append(String.format("EXP: [%s]\n", expected));
            msg.append(String.format("ACT: [%s]\n", actual));
            msg.append("DIF:  ");
            int j = 0;
            for (int i = 0; i + j < Math.max(actual.length(), expected.length()); i++) {
                if (actual.isEmpty() != expected.isEmpty() || i >= actual.length() || i >= expected.length() || actual.charAt(i + j) != expected.charAt(i)) {
                    msg.append("▲");
                    // j++;
                } else msg.append(" ");
            }
            failedAssert(msg.toString());

        }
    }

    private static void assertEquals(Polynom actual, Polynom expected, boolean asString) {
        if (asString)
            assertEquals(actual.toString(), expected.toString());
        else assertEquals(actual, expected);
    }

    private static void assertEquals(Polynom actual, Polynom expected) {
        assertEquals(actual, expected, "");
    }


    private static void assertEquals(Polynom actual, Polynom expected, String message) {
        if (!actual.toString().equals(expected.toString())) {
            assertEquals(actual.toString(), expected.toString(), message);
        } else {
            PolyNode n1 = actual.getHead();
            PolyNode n2 = expected.getHead();
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            while (n1 != null) {
                s1.append(String.format("[Coef: %.1f, Pow: %d] ", n1.getCoefficient() == 0 ? 0 : n1.getCoefficient(), n1.getPower() == 0 ? 0 : n1.getPower()));
                n1 = n1.getNext();
            }
            while (n2 != null) {
                s2.append(String.format("[Coef: %.1f, Pow: %d] ", n2.getCoefficient() == 0 ? 0 : n2.getCoefficient(), n2.getPower() == 0 ? 0 : n2.getPower()));
                n2 = n2.getNext();
            }

            assertEquals(s1.toString(), s2.toString(), message);
        }
    }

    private static void assertEquals(PolyNode actual, PolyNode expected) {
        if (equals(actual, expected))
            succeedAssert(String.format("%s and %s are equal", actual, expected));
        else
            failedAssert(String.format("Expected: %s, but got: %s", expected, actual));
    }

    private static boolean equals(PolyNode a, PolyNode b) {
        if ((a == null && b != null) || (a != null && b == null))
            return false;
        if (a == null && b == null)
            return true;
        return a.getCoefficient() == b.getCoefficient() && a.getPower() == b.getPower()
                && ((a.getNext() == null && b.getNext() == null)
                || (a.getNext() != null && b.getNext() != null
                && a.getNext().getCoefficient() == b.getNext().getCoefficient()
                && a.getNext().getPower() == b.getNext().getPower()));
    }

    private static void assertEquals(int a, int b) {
        assertEquals(a, b, "");
    }

    private static void assertEquals(int a, int b, String failMsg) {
        if (a == b)
            succeedAssert(String.format("%s and %s are equal", a, b));
        else if (failMsg.isEmpty())
            failedAssert(String.format("Expected %s but got %s", b, a));
        else
            failedAssert(String.format("Expected %s but got %s (%s)", b, a, failMsg));
    }


    private static void assertTrue(boolean b) {
        assertTrue(b, "");
    }

    private static void assertTrue(boolean b, String failMsg) {
        if (b)
            succeedAssert("True");
        else if (failMsg.isEmpty())
            failedAssert("Expected true, but got FALSE");
        else
            failedAssert(String.format("Expected true, but got FALSE (%s)", failMsg));
    }


    private static void assertFalse(boolean b) {
        if (!b)
            succeedAssert("False");
        else
            failedAssert("Expected false, but got TRUE");
    }

    private static void failedAssert(String message) {
        int lineOfCode = -1;

        for (StackTraceElement e : Thread.currentThread().getStackTrace()) {
            if (e.getMethodName().startsWith("lambda$")) {
                lineOfCode = e.getLineNumber();
                break;
            }
        }

        if (lineOfCode != -1) {
            vPrint(false, message + "\t-> FAIL (line: %d)\n", lineOfCode);
            message = String.format("At line %d: %s", lineOfCode, message);
        } else
            vPrint(false, message + "\t-> FAIL\n");

        ArrayList<String> msgs = methodErrorsMessages.get(cClass).getOrDefault(cMethod, new ArrayList<>());
        msgs.add(message);
        methodErrorsMessages.get(cClass).put(cMethod, msgs);
        methodErrorsCount.get(cClass).compute(cMethod, (k, c) -> c == null ? 0 : c + 1);
        errors++;
        errorsCurrent++;
    }

    private static void succeedAssert(String message) {
        vPrintln(false, message + "\t-> ok");
    }


    private static void vPrintln(String s) {
        vPrintln(true, s);
    }

    private static void vPrintln(Boolean v, String s) {
        vPrint(v, s + "\n");
    }

    private static void vPrint(String s, Object... o) {
        vPrint(true, s, o);
    }

    private static void vPrint(Boolean v, String s, Object... o) {
        if (v || VERBOSE)
            System.out.printf(s, o);
    }

    private static void report() {
        vPrintln("\n~~ Test is finished! ~~\n");
        vPrintln("===============================================\n" +
                "\t\t\t\tTESTS RESULTS\n" +
                "-----------------------------------------------");

        totalErrors.forEach((className, totalErrors) -> {
            vPrintln("-----------------------------------------------");
            vPrint("[%s] --> ", className.toUpperCase());
            if (totalErrors > 0) {
                vPrint("✖ Tests revealed %d errors ᕙ(⇀‸↼‶)ᕗ\n", totalErrors);
                methodErrorsCount.get(className).forEach((methodName, count) -> {
                    if (count > 0) {
                        vPrint("\t%s: %d errors\n", methodName, count);
                        methodErrorsMessages.get(className).get(methodName).forEach((msg) -> vPrintln("\t\t" + msg.replace("\n", "\n\t\t") + "\n"));
                        vPrintln("");
                    }
                });
            } else
                vPrintln("✓ Tests are passed with no errors ┐(￣ヮ￣)┌");
        });

        vPrintln("-----------------------------------------------");


        if (errors > 0)
            vPrint("\t\t\tTotal errors: %d\n", errors);
        else
            vPrint("All classes passed tests with no errors\n");

        vPrintln("===============================================");
        if (errors == 0)
            vPrint("Congratulations!\n☕ Good moment to consider buying me a cup coffee: %s ☕\n", new StringBuilder("iMfxaa/cc.kv//:sptth").reverse().toString());

        vPrintln("");
        vPrintln("What was your experience? Let me know: https://vk.cc/aiX8QX Thanks for your warm words!\n" +
                "Before reporting an issue make sure you use the latest verison.");
        vPrint("You're using version: %s, updates: %s\n\n", VERSION, UPDATES_LINK);

        vPrintln("~~ Please check your classes with official tester on forum ~~");
    }

    private static void reportMethodErrors(String methodName, int errorsCount) {
        String t = methodName.length() >= 25 ? "\t" : "";

        if (errorsCount == 0) {
            if (VERBOSE)
                vPrint("| OK\n\n", methodName);
            else
                vPrint("\t%-24s%s %-15s\n", methodName, t, "Ok");
        } else {
            if (VERBOSE)
                vPrint("| Errors: %d\n\n", errorsCount);
            else
                vPrint("\t%-24s%s Errors: %-15d\n", methodName, t, errorsCount);

        }
    }

    private static class ClassTest {
        private String className;
        private Test[] tests;

        ClassTest(String className, Test[] tests) {
            this.className = className;
            this.tests = tests;
        }

        String getClassName() {
            return className;
        }

        Test[] getTests() {
            return tests;
        }
    }

    private static class Test {
        private String name;
        private Runnable test;

        Test(String name, Runnable test) {
            this.name = name;
            this.test = test;
        }

        String getName() {
            return name;
        }

        Runnable getTest() {
            return test;
        }
    }
}