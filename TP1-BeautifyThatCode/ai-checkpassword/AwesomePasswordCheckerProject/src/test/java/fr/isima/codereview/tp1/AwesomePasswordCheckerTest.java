package fr.isima.codereview.tp1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.net.URL;

class AwesomePasswordCheckerTest {
    private AwesomePasswordChecker checker;
    
    @BeforeEach
    void setUp() throws IOException {
        StringBuilder clusterData = new StringBuilder();
        for (int i = 0; i < 28; i++) {
            clusterData.append("1.0");
            if (i < 27) clusterData.append(";");
        }
        clusterData.append("\n");
        
        ByteArrayInputStream bis = new ByteArrayInputStream(clusterData.toString().getBytes());
        checker = new AwesomePasswordChecker(bis);
    }
    
    @Test
    void testGetInstanceFromFile() throws IOException {
        StringBuilder clusterData = new StringBuilder();
        for (int i = 0; i < 28; i++) {
            clusterData.append("1.0");
            if (i < 27) clusterData.append(";");
        }
        clusterData.append("\n");
        
        File tempFile = File.createTempFile("test_clusters", ".csv");
        tempFile.deleteOnExit();
        java.nio.file.Files.write(tempFile.toPath(), clusterData.toString().getBytes());
        
        AwesomePasswordChecker instance1 = AwesomePasswordChecker.getInstance(tempFile);
        AwesomePasswordChecker instance2 = AwesomePasswordChecker.getInstance(tempFile);
        
        Assertions.assertSame(instance1, instance2, "Le singleton ne retourne pas la même instance");
    }
    
    @Test
    void testGetInstanceFromResource() throws IOException {
        AwesomePasswordChecker instance1 = AwesomePasswordChecker.getInstance();
        AwesomePasswordChecker instance2 = AwesomePasswordChecker.getInstance();
        
        Assertions.assertSame(instance1, instance2, "Le singleton ne retourne pas la même instance à partir d'une ressource");
    }
    
    @Test
    void testMaskAff() {
        String password = "password123!";
        int[] mask = checker.maskAff(password);
        
        Assertions.assertEquals(28, mask.length, "Le masque doit avoir une longueur de 28");
        
        Assertions.assertEquals(2, mask[0], "Le masque pour 'p' devrait être 2");
        Assertions.assertEquals(1, mask[1], "Le masque pour 'a' devrait être 1"); 
        Assertions.assertEquals(5, mask[8], "Le masque pour '1' devrait être 5"); 
        Assertions.assertEquals(6, mask[11], "Le masque pour '!' devrait être 6");
        
        for (int i = 12; i < 28; i++) {
            Assertions.assertEquals(0, mask[i], "Les positions après le mot de passe doivent être 0");
        }
    }
    
    @Test
    public void testGetDistance() throws IOException {
        String password = "testPassword123!";
        double distance = checker.getDistance(password);
        
        Assertions.assertTrue(distance >= 0, "La distance devrait être positive");
        
        // Test avec un mot de passe vide
        assertThrows(IllegalArgumentException.class, () -> checker.getDistance(""),
            "Un mot de passe vide devrait lever une IllegalArgumentException");
            
        // Test avec un mot de passe null
        assertThrows(IllegalArgumentException.class, () -> checker.getDistance(null),
            "Un mot de passe null devrait lever une IllegalArgumentException");
    }
    
    @Test
    void testComputeMD5() {
        String password = "password123";
        String md5Hash = AwesomePasswordChecker.computeMD5(password);
        
        Assertions.assertNotNull(md5Hash, "Le hash MD5 ne doit pas être nul");
        Assertions.assertEquals(32, md5Hash.length(), "Le hash MD5 doit avoir une longueur de 32 caractères");
        Assertions.assertTrue(md5Hash.matches("[0-9a-fA-F]{32}"), "Le hash MD5 doit être en format hexadécimal");
        
        String expectedHash = "482c811da5d5b4bc6d497ffa98491e38";
        Assertions.assertEquals(expectedHash, md5Hash, "Le hash MD5 ne correspond pas à la valeur attendue");
    
        assertThrows(IllegalArgumentException.class, () -> AwesomePasswordChecker.computeMD5(null),
            "Une entrée null devrait lever une IllegalArgumentException");
    }
}
