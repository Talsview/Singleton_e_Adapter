# Padrões de Projeto em Java: Singleton e Adapter

Este projeto apresenta dois exemplos simples de padrões de projeto em Java:

- Singleton
- Adapter

O objetivo é mostrar como esses padrões funcionam na prática, usando exemplos fáceis de entender e aplicar.

---

## Tecnologias utilizadas

- Java
- Biblioteca padrão do Java
- `java.util.logging.Logger`

---

# 1. Singleton

## O que é Singleton?

O padrão Singleton é usado quando queremos garantir que uma classe tenha apenas **uma única instância** durante toda a execução do programa.

Ou seja, em vez de criar vários objetos com `new`, a própria classe controla sua criação.

---

## Exemplo utilizado

Neste projeto, o Singleton foi aplicado em um **Diário de Bordo**.

A ideia é que exista apenas um diário principal para registrar os acontecimentos da nave.

---

## Classes do Singleton

```text
DiarioDeBordo.java
MainSingleton.java
```

---

## Exemplo do código

```java
public class DiarioDeBordo {

    private static DiarioDeBordo instancia;

    private DiarioDeBordo() {
        System.out.println("Diário de bordo criado.");
    }

    public static DiarioDeBordo getInstancia() {
        if (instancia == null) {
            instancia = new DiarioDeBordo();
        }

        return instancia;
    }

    public void registrar(String mensagem) {
        System.out.println("Registro no diário: " + mensagem);
    }
}
```

---

## Como funciona?

O construtor da classe é privado:

```java
private DiarioDeBordo() {
}
```

Isso impede que outras classes criem objetos diretamente usando:

```java
new DiarioDeBordo();
```

A única forma de acessar o objeto é pelo método:

```java
DiarioDeBordo.getInstancia();
```

Esse método verifica se a instância já existe.

Se não existir, ele cria.  
Se já existir, ele retorna a mesma instância.

---

## Exemplo de uso

```java
public class MainSingleton {

    public static void main(String[] args) {

        DiarioDeBordo diario1 = DiarioDeBordo.getInstancia();
        DiarioDeBordo diario2 = DiarioDeBordo.getInstancia();

        diario1.registrar("A nave iniciou a viagem.");
        diario2.registrar("Sinal estranho detectado no radar.");

        if (diario1 == diario2) {
            System.out.println("Os dois diários são o mesmo objeto.");
        }
    }
}
```

---

## Saída esperada

```text
Diário de bordo criado.
Registro no diário: A nave iniciou a viagem.
Registro no diário: Sinal estranho detectado no radar.
Os dois diários são o mesmo objeto.
```

---

# 2. Adapter

## O que é Adapter?

O padrão Adapter é usado quando um sistema precisa utilizar uma classe, biblioteca ou código que possui uma forma diferente de funcionamento.

Ele funciona como uma ponte entre dois formatos incompatíveis.

---

## Exemplo utilizado

Neste projeto, o Adapter foi aplicado em um sistema de registro de eventos de uma nave.

O sistema precisa registrar eventos usando um método simples:

```java
registrar(String setor, String mensagem, int gravidade);
```

Porém, para melhorar o sistema, foi usada a biblioteca padrão do Java:

```java
java.util.logging.Logger
```

Como o `Logger` trabalha com outro formato, foi criado um Adapter para fazer essa adaptação.

---

## Classes do Adapter

```text
Registro.java
LoggerAdapter.java
Nave.java
Main.java
```

---

## Interface Registro

```java
public interface Registro {

    void registrar(String setor, String mensagem, int gravidade);
}
```

Essa interface define o formato que a classe `Nave` espera usar para registrar eventos.

---

## Classe LoggerAdapter

```java
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerAdapter implements Registro {

    private Logger logger;

    public LoggerAdapter() {
        logger = Logger.getLogger("DiarioDeBordo");
    }

    @Override
    public void registrar(String setor, String mensagem, int gravidade) {
        Level nivel;

        if (gravidade == 1) {
            nivel = Level.INFO;
        } else if (gravidade == 2) {
            nivel = Level.WARNING;
        } else {
            nivel = Level.SEVERE;
        }

        logger.log(nivel, "[" + setor + "] " + mensagem);
    }
}
```

---

## Classe Nave

```java
public class Nave {

    private Registro registro;

    public Nave(Registro registro) {
        this.registro = registro;
    }

    public void iniciarMissao() {
        registro.registrar("Motor", "Motor principal ligado", 1);
        registro.registrar("Radar", "Objeto desconhecido detectado", 2);
        registro.registrar("Oxigênio", "Nível crítico de oxigênio", 3);
    }
}
```

---

## Classe Main

```java
public class Main {

    public static void main(String[] args) {

        Registro registro = new LoggerAdapter();

        Nave nave = new Nave(registro);

        nave.iniciarMissao();
    }
}
```

---

## Como funciona o Adapter?

A classe `Nave` usa apenas a interface `Registro`.

Ela não sabe que por trás está sendo usada a biblioteca `Logger`.

A chamada simples:

```java
registro.registrar("Radar", "Objeto desconhecido detectado", 2);
```

É adaptada para:

```java
logger.log(Level.WARNING, "[Radar] Objeto desconhecido detectado");
```

Ou seja, o `LoggerAdapter` transforma a gravidade numérica do sistema em níveis do Logger:

```text
1 → INFO
2 → WARNING
3 → SEVERE
```

---

## Saída esperada

A saída pode aparecer parecida com esta:

```text
INFO: [Motor] Motor principal ligado
WARNING: [Radar] Objeto desconhecido detectado
SEVERE: [Oxigênio] Nível crítico de oxigênio
```

Dependendo da versão do Java, o Logger também pode exibir data, horário e nome da classe.

---

# Diferença entre Singleton e Adapter

| Padrão | Objetivo |
|---|---|
| Singleton | Garantir que exista apenas uma instância de uma classe |
| Adapter | Adaptar uma classe ou biblioteca para funcionar com outro formato |

---

# Resumo dos exemplos

## Singleton

No exemplo do Singleton, a classe `DiarioDeBordo` só pode ter um objeto criado.

Mesmo usando:

```java
DiarioDeBordo diario1 = DiarioDeBordo.getInstancia();
DiarioDeBordo diario2 = DiarioDeBordo.getInstancia();
```

As duas variáveis apontam para o mesmo objeto.

---

## Adapter

No exemplo do Adapter, a classe `Nave` usa uma interface simples chamada `Registro`.

Porém, quem registra as mensagens de verdade é o `Logger` do Java.

A classe `LoggerAdapter` faz a ponte entre o formato simples do sistema e o formato da biblioteca `Logger`.

---

# Como executar

Compile os arquivos Java pelo terminal:

```bash
javac *.java
```

Para executar o exemplo Singleton:

```bash
java MainSingleton
```

Para executar o exemplo Adapter:

```bash
java Main
```

---

# Conclusão

Este projeto demonstra dois padrões de projeto importantes em Java.

O Singleton foi usado para garantir que exista apenas um Diário de Bordo no sistema.

O Adapter foi usado para permitir que a classe `Nave` use um sistema simples de registro, enquanto internamente os eventos são registrados com a biblioteca `Logger` do Java.

Assim, o projeto mostra como aplicar padrões de projeto para organizar melhor o código e facilitar futuras alterações.
