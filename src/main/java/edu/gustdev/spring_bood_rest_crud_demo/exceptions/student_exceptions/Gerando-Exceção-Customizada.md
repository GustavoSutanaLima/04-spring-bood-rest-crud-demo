# Gerando uma Exceção Customizada e retornando um JSON
## Passo 1: Definindo a Classe de Response:
* A primeira coisa a se fazer é definir um JSON de como a resposta será retoranda ao usuário:

    ```json
        {
            "status": 404,
            "messege": "Student id not found - {studentId}",
            "timeStamp": 156149650271
        }
    ```
* A partir desse JSON, definiremos uma classe ```StudentErrorResponse```, ou seja, esse objeto será retornado na requisição, e não mais teremos uma resposta padrão como a abaixo:
    ```json
        {
            "timestamp": "2024-09-26T15:39:17.127+00:00",
            "status": 500,
            "error": "Internal Server Error",
            "path": "/spring-boot-rest-crud-demo/api/students/9999"
        }
    ```
* Definindo a classe ```StudentErrorResponse``` de fato:

    ```java
        public class StudentErrorResponse {

            //fields (atributos)
            private int status;
            private String messege;
            private long timeStamp;

            //construtores

            //getters e setters (padrão java beans) <--- IMPORTANTÍSSIMO
    }
    ```
    * É estritamente necessário que os getters e setters de todos os atributos estajam devidamente declarados, porque o Spring Boot, usa, em seu back-end, o Jackson que fazer a conversão do objeto para seu JSON e vice-versa.

## Passo 2: Criando a exceção customizada:
* Definindo a exceção customizada caso não haja um objeto para um determinado request, por exemplo, no banco de dados não existe um Student de id = 3. Aqui definiremos a exceção como ```StudentNotFoundException``` que extende da execeção ```RuntimeException```:

    ```java
        public class StudentNotFoundException extends RuntimeException {

            //Definindo construtores a partir da super class (ou classe pai, neste caso, RuntimeException)
            public StudentNotFoundException(String message) {
                super(message);
            }
        } 
    ```
    * Comentário a cerca da utilização de do método **super()**:
        * **Construtor da Subclasse (classe filha)**: O construtor **StudentNotFoundException(String message)** recebe um atributo do tipo String, **message**.
        * **Chamada ao Construtor da Superclasse (classe mãe)**: super(message) chama o construtor da superclasse RuntimeException que aceita uma string. Isso permite que a mensagem de erro seja armazenada e recuperada posteriormente usando métodos como getMessage() da classe ```RuntimeException```.
        * Este é um conceito básico da Orientação a objetos chamado **herança**, onde uma classe herda (ou *extends* - em Java) todos os métodos da super classe;

## Passo 3: Fazer a implementação da exceção na camada de Serviço
* <u>Boas práticas referentes a camada de serviço: ela é responsável pela lógica de negócios. Portanto, é aqui que você deve lançar exceções relacionadas a regras de negócios ou operações específicas do serviço</u>. Se um estudante não for encontrado no banco de dados, a camada de serviço deve lançar uma StudentNotFoundException.

### Passo 3.1: Implementação de fato:
* A exeção ```StudentNotFoundException(String message)``` é lançada dentro do argumento de orElseThrow() via expressão lambda (programação funcional);
        
    ```java
    public class StudentServiceImplementacao implements StudentService {

        /* outros atribuos de métodos ...*/

    //Implementação da lançamento da no método getStudentById(Integer id):  
    @Override
        public Student getStudentById(Integer id) {
        return studentRepo
                    .findById(id)
                    .orElseThrow(
                        () -> new StudentNotFoundException("O estudante de id " + id + " não foi encontrado")
                        );
        }
    }
        ```


## Passo 4: Utilização da anotação ```@ExceptionHandler```:
* A anotação @ExceptionHandler lida de fato com a exeção, ou seja, o Spring Boot faz algo de fato, após a exceção ser lançada.
* Por isso, com a ajuda de um metodo anotado com @ExceptionHanlder, o objeto criado no passo 1, (```StudentErrorResponse```), caso um Stundet com um determinado ID não for encontrado no banco de dados, retornamentos um objeto desse tipo. 
* Porém, para que isso seja possível é necessário criar uma classe para que as exceções sejam gerenciadas pelo Spring Boot.
* Esta classe precisa estar anotada com ```@ControllerAdive```. Em nosso exemplo, a classe vai ser chamada de ```GlobalCustomExceptionHandler```.
* Após criada a classe é preciso definir um método que de fato será anotado com ```@ExceptionHandler``` e em seu argumento, a classe da exceção que será lançada na camada de serviço.
    * O método criado abaixo se chama ```handlerStudentNotFoundException(StudentNotFoundException exc)```, ele recebe um argumento é que do tipo StudentNotFoundException, esta classe, por herdar de RuntimeException, possui um método chama getMessage() - que retona a mensagem passada em seu argumento; Esse método handler, retornará um objeto do tipo ResponseEntity, mas em vez dessa entidade ser uma ```<String>``` será do tipo que criamos no passo 1, ```<StudentErrorResponse>```. Dentro do ResponseEntity, damos um ```new``` (instanciamos) um novo ```StudentErrorResponse```, passando os devidos argumentos dessa classe, e complementamos o ResponseEntity com ```HttpStatus.NOT_FOUND```.

        ```java
        @ControllerAdvice
        public class GlobalCustomExceptionHandler {

            @ExceptionHandler(StudentNotFoundException.class)
            public ResponseEntity<StudentErrorResponse> handlerStudentNotFoundException(StudentNotFoundException exc) {
                return new ResponseEntity<StudentErrorResponse>(
                    new StudentErrorResponse(
                        404, exc.getMessage(), System.currentTimeMillis()),HttpStatus.NOT_FOUND
                        );
            }
        }
        ```