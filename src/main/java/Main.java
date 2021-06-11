import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.json.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static final String PATH = "C:\\Users\\arturs.maslenkovs\\OneDrive - HELMES A S\\Töölaud\\JAVA\\XMLExample.xml";
    static final String FOLDER_PATH = "C:\\Users\\arturs.maslenkovs\\OneDrive - HELMES A S\\Töölaud\\JAVA";

    public static void main(String[] args) {
//        try {
//            Path path = Paths.get(PATH);
//            Path folderPath = Paths.get(FOLDER_PATH);
//
//            List<String> stringList = Files.readAllLines(path);
//            System.out.println(stringList);
//
//            BufferedWriter bufferedWriter = Files.newBufferedWriter(path);
//            bufferedWriter.write("My name is Artur");
//            bufferedWriter.newLine();
//            bufferedWriter.write("My age is 0");
//            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Path path = Paths.get(PATH);
//
//        Properties properties = new Properties();
//        try {
//            properties.load(Files.newInputStream(path));
//            String name = properties.getProperty("name");
//            Integer age = Integer.valueOf(properties.getProperty("age"));
//
//            System.out.println(name + age);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Path path = Paths.get(PATH);
//        try {
//            JsonReader jsonReader = Json.createReader(Files.newInputStream(path));
//            JsonObject jsonObject = jsonReader.readObject();
//            jsonReader.close();
//            Employee employee = new Employee();
//
//            employee.setId(jsonObject.getInt("id"));
//            employee.setName(jsonObject.getString("name"));
//            employee.setPermanent(jsonObject.getBoolean("permanent"));
//
//            JsonObject jsonAddress = jsonObject.getJsonObject("address");
//            String street = jsonAddress.getString("street");
//            String city = jsonAddress.getString("city");
//            Integer zipCode = jsonAddress.getInt("zipcode");
//            employee.setAddress(new Address(street, city, zipCode));
//
//            JsonArray jsonValues = jsonObject.getJsonArray("phoneNumbers");
//            List<Long> phoneNumbers = new ArrayList<>();
//            for (JsonValue value : jsonValues) {
//                phoneNumbers.add(Long.valueOf(value.toString()));
//            }
//            employee.setPhoneNumbers(phoneNumbers);
//
//            employee.setRole(jsonObject.getString("role"));
//            System.out.println(employee.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            Path path = Paths.get(PATH);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(Files.newInputStream(path));

            document.getDocumentElement().normalize();
            Node classNode = document.getElementsByTagName("class").item(0);
            NodeList studentNodeList = classNode.getChildNodes();

            for (int i = 0; i < studentNodeList.getLength(); i++) {
                Node studentNode = studentNodeList.item(i);

                if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) studentNode;
//                    System.out.println(element.getNodeName() + " " + element.getAttribute("rollno"));
                }

                NodeList studentNodeElements = studentNode.getChildNodes();
                for (int k = 1; k < studentNodeElements.getLength(); k++) {
                    if (studentNodeElements.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        Element temp = (Element) studentNodeElements.item(k);
//                        System.out.println(temp.getNodeName() + " " + temp.getTextContent());
                    }
                }
//                System.out.println();
            }
            Node myStudentNode = studentNodeList.item(1).cloneNode(true);
            NodeList myStudentNodeList = myStudentNode.getChildNodes();
            for (int i = 0; i < myStudentNodeList.getLength(); i++) {
                if (myStudentNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) myStudentNodeList.item(i);
                    element.setTextContent("Artur");
                }
            }
//            System.out.println(myStudentNode.getTextContent());
            classNode.appendChild(myStudentNode);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new FileOutputStream(String.valueOf(path)));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | SAXException | TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
