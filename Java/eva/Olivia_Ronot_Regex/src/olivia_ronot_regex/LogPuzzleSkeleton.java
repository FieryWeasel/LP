package olivia_ronot_regex;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogPuzzleSkeleton {

    public static void main(String[] args) {
        String regex = "\\S*puzzle\\S*.jpg";
        List<URL> urlsToDownload = read_urls("place_code.google.com", regex);
        //TODO: Compléter avec le chemin du dossier destination
        downloadImages(urlsToDownload, "result/");
    }

    private static List<URL> read_urls(String filename, String regex) {
        /*
         * Returns a list of the puzzle urls from the given log file, extracting
         * the hostname from the filename itself. Screens out duplicate urls and
         * returns the urls sorted into increasing order.
         */
        String base_url = extractBaseUrlFromFileName(filename);

        //TODO: Regex pour extraire les bonnes url
        Pattern urlPattern = Pattern.compile(regex);

        List<URL> fullUrls = null;

        BufferedReader f = null;

        try {
            f = new BufferedReader(new FileReader(new File(filename)));
            List<String> urls = findAllUrlsMatchingPattern(urlPattern, f);
            urls = removeDuplicates(urls);
            sortUrlList(urls);
            fullUrls = formatFullUrls(base_url, urls);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(f);
        }

        return fullUrls;
    }

    private static String extractBaseUrlFromFileName(String filename) {
        //TODO
        String regexFileName = "_(\\S*)";
        Pattern pattern = Pattern.compile(regexFileName);
        Matcher matcher = pattern.matcher(filename);
        matcher.find();
        String baseUrl = matcher.group(1);
        return baseUrl;
    }

    private static List<String> findAllUrlsMatchingPattern(Pattern urlPattern, BufferedReader reader)
            throws IOException {
        String line;
        List<String> urls = new ArrayList<String>();
        //TODO: extraire les URL qui suivent le pattern        

        while ((line = reader.readLine()) != null) {
            Matcher matcher = urlPattern.matcher(line);
            if (matcher.find()) {
                urls.add(matcher.group());
            }
        }

        return urls;
    }

    private static List<String> removeDuplicates(List<String> list) {
        //TODO: supprimer les doublons
        Set<String> set = new LinkedHashSet<>(list);
        List<String> duplicatesRemoved = new ArrayList();
        duplicatesRemoved.addAll(set);
        return duplicatesRemoved;
    }

    private static void sortUrlList(List<String> urls) {
        //TODO: trier la liste
        Comparator<String> comparator = new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                String regexCompare = "-(\\S{4}?)\\.jpg";
                Pattern pattern = Pattern.compile(regexCompare);
                Matcher matcher;
                
                matcher = pattern.matcher(o1);
                matcher.find();
                String string1 = matcher.group(1);
                
                matcher = pattern.matcher(o2);
                matcher.find();
                String string2 = matcher.group(1);
                
                return string1.compareTo(string2);
            }
        };
        
        Collections.sort(urls, comparator);

    }

    private static List<URL> formatFullUrls(String baseUrl, List<String> urls)
            throws MalformedURLException {
        List<URL> fullUrls = new ArrayList<URL>();
        for (String urlString : urls) {
            fullUrls.add(new URL("https://" + baseUrl + urlString));
        }
        return fullUrls;
    }

    private static void downloadImages(List<URL> imgUrls, String destDirName) {

        // Create destination directory if it doesn't exist
        File destDir = new File(destDirName);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        
        String dest = destDirName + "index.html";
        // Create html file
        Path htmlFilePath = Paths.get(dest);

        PrintWriter htmlFile = null;

        try {
            htmlFile = new PrintWriter(Files.newBufferedWriter(htmlFilePath,
                    StandardCharsets.UTF_8, StandardOpenOption.CREATE));
            htmlFile.println("<html><body>");

            int i = 0;
            for (URL url : imgUrls) {
                i++;
                String imgFileName = "img" + i + ".jpg";
                htmlFile.print("<img src=\"" + imgFileName + "\">");
                dest = destDirName + imgFileName;
                Path destinationPath = Paths.get(dest);
                if (!destinationPath.toFile().exists()) {
                    Files.copy(url.openStream(), destinationPath,
                            StandardCopyOption.REPLACE_EXISTING);
                }
            System.out.println(destinationPath.toString());
                
            }

            htmlFile.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(htmlFile);
            System.out.println("file finished");
        }

    }

    public static void close(Closeable c) {
        if (c == null) {
            return;
        }
        try {
            c.close();
        } catch (IOException e) {
            //log the exception
        }
    }

}
