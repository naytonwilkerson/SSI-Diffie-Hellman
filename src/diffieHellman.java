import java.util.Scanner;

public class diffieHellman {
	private static Scanner sc;
    public static void main(final String[] args) throws Exception {

    	sc = new Scanner(System.in);
        new diffieHellman().init();
        
    }
    
    private void init() {

 
         persona alice = new persona();
         persona bob   = new persona();

        //    O                                        O
        //   /|\                                      /|\
        //   / \                                      / \
        //  ALICE                                     BOB

        // 2. ------------------------------------------------------------------
        //Alice e Bob geram chaves públicas e privadas.

        alice.generateKeys();
        bob.generateKeys();

        //    O                                        O
        //   /|\                                      /|\
        //   / \                                      / \
        //  ALICE                                     BOB
        //  _ CHAVE PUBLICA                          _ CHAVE PUBLICA
        //  _ cHAVE PRIVADA                          _CHAVE PRIVADA

        // 3. ------------------------------------------------------------------
        // Alice e Bob trocam chaves públicas entre si.

        alice.receivePublicKeyFrom(bob);
        bob.receivePublicKeyFrom(alice);

        //    O                                        O
        //   /|\                                      /|\
        //   / \                                      / \
        //  ALICE                                     BOB
        //  + chave publica                          + chave publica
        //  + chave privada                          + chave privada
        //  _ chave publica <----------------------->_ chave publica

        // 4. ------------------------------------------------------------------
        // Alice gera uma chave secreta comum usando sua chave privada e a chave pública de Bob.
        // Bob gera uma chave secreta comum usando sua chave privada e a chave pública de Alice.
        // Ambas as chaves secretas são iguais sem TRANSFERIR.
        alice.generateCommonSecretKey();
        bob.generateCommonSecretKey();
        
        //    O                                        O
        //   /|\                                      /|\
        //   / \                                      / \
        //  ALICE                                     BOB
        //  + chave publica                         + chave publica 
        //  + chave privada                         + chave privada
        //  + chave publica                         + chave publica
        //  _ chave secreta                         _ chave secreta

        // 5. ------------------------------------------------------------------
        //Alice criptografa a mensagem usando a chave secreta e envia para Bob

        System.out.println("Digite mensagem de alice para bob:");
        alice.encryptAndSendMessage(sc.nextLine(), bob);
        System.out.println("Digite mensagem de Bob para alice:");
        bob.encryptAndSendMessage(sc.nextLine(), alice);

        //
        //    O                                        O
        //   /|\ []-------------------------------->  /|\
        //   / \                                      / \
        //
        //  ALICE                                     BOB
        //  + chave publica                         + chave publica
        //  + chave privada                         + chave privada
        //  + chave publica                         + chave publica
        //  + chave secreta                         + chave secreta
        //  + mensagem                              _ mensagem

        // 6. ------------------------------------------------------------------
        // Bob recebe a mensagem importante e descriptografa com chave secreta.

        bob.whisperTheSecretMessage();

        //
        //    O                     (((   (((   (((   \O/   )))
        //   /|\                                       |
        //   / \                                      / \
        //
        //  ALICE                                     BOB
        //  + chave publica                         + chave publica
        //  + chave privada                         + chave privada
        //  + chave publica                         + chave publica
        //  + chave secreta                         + chave secreta
        //  + mensagem                              + mensagem
        
        System.out.println();
        System.out.println();
        System.out.println("Chave publica de bob "+bob.getPublicKey());
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Mensagem de alice para Bob: ");
        bob.whisperTheSecretMessage();
        System.out.println("------------------------------------------------------------------------------------");
        
        System.out.println("Chave publica de alice "+alice.getPublicKey());
        System.out.println("------------------------------------------------------------------------------------");
        
        System.out.println("Mensagem de Bob pra alice: ");
        alice.whisperTheSecretMessage();
    }
}