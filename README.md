# Padrões de Projeto em Java: Singleton e Adapter

Este projeto apresenta dois padrões de projeto em Java:

- Singleton
- Adapter

O objetivo é mostrar como esses padrões funcionam na prática usando exemplos simples e fáceis de entender.

---

## Tecnologias utilizadas

- Java
- Biblioteca padrão do Java
- `java.util.logging.Logger`

---

# 1. Singleton

## O que é Singleton?

O padrão Singleton é usado quando queremos garantir que uma classe tenha apenas **uma única instância** durante toda a execução do programa.

Ou seja, o sistema inteiro usa o mesmo objeto.

---

## Exemplo utilizado

Neste projeto, o Singleton foi aplicado em uma **Central de Energia da Nave**.

A ideia é que a nave tenha apenas uma central principal responsável por controlar a energia.

Mesmo que diferentes partes do sistema acessem essa central, todas usam a mesma instância.

---

## Classes do Singleton

```text
CentralEnergia.java
MainSingleton.java
```

---

## Classe `CentralEnergia.java`

```java
public enum CentralEnergia {

    INSTANCIA;

    private int energia = 100;

    public void consumirEnergia(String sistema, int quantidade) {
        energia -= quantidade;

        if (energia < 0) {
            energia = 0;
        }

        System.out.println(sistema + " consumiu " + quantidade + "% de energia.");
    }

    public void recarregarEnergia(int quantidade) {
        energia += quantidade;

        if (energia > 100) {
            energia = 100;
        }

        System.out.println("Energia recarregada em " + quantidade + "%.");
    }

    public void mostrarEnergia() {
        System.out.println("Energia atual da nave: " + energia + "%");
    }
}
```

---

## Classe `MainSingleton.java`

```java
public class MainSingleton {

    public static void main(String[] args) {

        CentralEnergia central1 = CentralEnergia.INSTANCIA;
        CentralEnergia central2 = CentralEnergia.INSTANCIA;

        central1.consumirEnergia("Motor principal", 20);
        central2.consumirEnergia("Radar", 15);

        central1.mostrarEnergia();

        central2.recarregarEnergia(10);

        central1.mostrarEnergia();

        if (central1 == central2) {
            System.out.println("As duas variáveis usam a mesma central de energia.");
        }
    }
}
```

---

## Como funciona o Singleton com enum?

Neste exemplo, o Singleton foi feito usando `enum`.

A linha abaixo representa a única instância da central de energia:

```java
INSTANCIA;
```

Sempre que o sistema usa:

```java
CentralEnergia.INSTANCIA
```

ele está acessando o mesmo objeto.

Por isso, quando `central1` consome energia e `central2` recarrega energia, as duas estão alterando a mesma central.

---

## Saída esperada

```text
Motor principal consumiu 20% de energia.
Radar consumiu 15% de energia.
Energia atual da nave: 65%
Energia recarregada em 10%.
Energia atual da nave: 75%
As duas variáveis usam a mesma central de energia.
```

---

# 2. Adapter

## O que é Adapter?

O padrão Adapter é usado quando um sistema precisa utilizar uma classe, biblioteca ou recurso que possui uma forma diferente de funcionamento.

Ele funciona como uma ponte entre dois formatos incompatíveis.

---

## Exemplo utilizado

Neste projeto, o Adapter foi aplicado em um sistema de registro de eventos de uma nave.

A nave registra eventos usando um formato simples:

```java
registrar(String setor, String mensagem, int gravidade);
```

Porém, o Java possui uma biblioteca própria para registro de logs:

```java
java.util.logging.Logger
```

Como o `Logger` trabalha de uma forma diferente, foi criado um Adapter para adaptar o sistema ao formato da biblioteca.

---

## Classes do Adapter

```text
Registro.java
LoggerAdapter.java
Nave.java
Main.java
```

---

## Interface `Registro.java`

```java
public interface Registro {

    void registrar(String setor, String mensagem, int gravidade);
}
```

Essa interface define o formato que a classe `Nave` espera usar para registrar eventos.

---

## Classe `LoggerAdapter.java`

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

## Classe `Nave.java`

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

## Classe `Main.java`

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

Ela não sabe que por trás está sendo usado o `Logger` do Java.

A nave chama este método:

```java
registro.registrar("Radar", "Objeto desconhecido detectado", 2);
```

O `LoggerAdapter` recebe essa chamada e transforma a gravidade numérica em um nível do `Logger`.

A conversão funciona assim:

```text
1 → INFO
2 → WARNING
3 → SEVERE
```

Então esta chamada:

```java
registro.registrar("Radar", "Objeto desconhecido detectado", 2);
```

é adaptada para algo equivalente a:

```java
logger.log(Level.WARNING, "[Radar] Objeto desconhecido detectado");
```

---

## Saída esperada

A saída pode aparecer parecida com esta:

```text
INFO: [Motor] Motor principal ligado
WARNING: [Radar] Objeto desconhecido detectado
SEVERE: [Oxigênio] Nível crítico de oxigênio
```

Dependendo da versão do Java, o `Logger` também pode exibir data, horário e nome da classe.

---

# Diferença entre Singleton e Adapter

| Padrão | Objetivo |
|---|---|
| Singleton | Garantir que exista apenas uma instância de uma classe |
| Adapter | Adaptar uma classe ou biblioteca para funcionar com outro formato |

---

# Resumo dos exemplos

## Singleton

No exemplo do Singleton, a `CentralEnergia` representa a central principal de energia da nave.

Como foi usado `enum`, existe apenas uma instância:

```java
CentralEnergia.INSTANCIA
```

Assim, qualquer parte do sistema que acessar essa central estará usando o mesmo objeto.

---

## Adapter

No exemplo do Adapter, a classe `Nave` registra eventos usando a interface `Registro`.

Porém, quem registra os eventos de verdade é o `Logger` do Java.

A classe `LoggerAdapter` faz a ponte entre o formato simples do sistema e o formato usado pela biblioteca `Logger`.

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

O Singleton foi usado para garantir que exista apenas uma Central de Energia no sistema da nave.

O Adapter foi usado para permitir que a classe `Nave` continue usando um método simples de registro, enquanto internamente os eventos são registrados com a biblioteca `Logger` do Java.

Assim, o projeto mostra como padrões de projeto ajudam a organizar o código e facilitar futuras mudanças.
