package com.codepumpkin.behavioral;

import java.util.ArrayList;

/**
 * Esta interface lida com a adição, exclusão e atualização de todos os observadores
 */
interface Sujeito {
    public void registrar(Observador o);

    public void desregistrar(Observador o);

    public void notificarTodosOsObservadores(String mensagem);
}

/**
 * O método update dos Observadores é chamado quando o Sujeito muda
 */
interface Observador {
    public void atualizar(String nome, String mensagem);
}

/**
 * Esta classe implementa a interface Sujeito.
 */
class Celebridade implements Sujeito {

    private String nomeCelebridade; // nome da celebridade
    private ArrayList<Observador> seguidores; // lista de seguidores

    public Celebridade(String nomeCelebridade) {
        this.nomeCelebridade = nomeCelebridade;
        seguidores = new ArrayList<Observador>();
    }

    /**
     * adiciona seguidor à lista de seguidores registrados da celebridade
     */
    @Override
    public void registrar(Observador o) {
        seguidores.add(o);
        System.out.println(o + " começou a seguir " + nomeCelebridade);
    }

    /**
     * remove seguidor da lista de seguidores registrados da celebridade
     */
    @Override
    public void desregistrar(Observador o) {
        seguidores.remove(o);
        System.out.println(o + " deixou de seguir " + nomeCelebridade);
    }

    /**
     * Notifica todos os seguidores registrados
     */
    @Override
    public void notificarTodosOsObservadores(String mensagem) {
        for (Observador seguidor : seguidores) {
            seguidor.atualizar(nomeCelebridade, mensagem);
        }
        System.out.println();
    }

    /**
     * Este método atualiza a mensagem. Ele chamará internamente
     * notificarTodosOsObservadores(mensagem) após atualizar a mensagem.
     */
    public void tuitar(String mensagem) {

        System.out.println("\n" + nomeCelebridade + " tuitou :: " + mensagem + "\n");

        notificarTodosOsObservadores(mensagem);
    }

}

/**
 * Esta classe implementa a interface Observador.
 */
class Seguidor implements Observador {

    private String nomeSeguidor;

    public Seguidor(String nomeSeguidor) {
        this.nomeSeguidor = nomeSeguidor;
    }

    /**
     * Este método será chamado para atualizar todos os seguidores sobre o novo
     * tuíte postado pela celebridade.
     */
    @Override
    public void atualizar(String nomeCelebridade, String mensagem) {
        System.out.println(nomeSeguidor + " recebeu o tuíte de " + nomeCelebridade + " :: " + mensagem);

    }

    @Override
    public String toString() {
        return nomeSeguidor;
    }

}

public class DemoPadraoObserver {

    public static void main(String[] args) {
        Celebridade amirkhan = new Celebridade("Amirkhan");
        Celebridade selenaGomez = new Celebridade("Selena Gomez");

        Seguidor amar = new Seguidor("Amar");
        Seguidor juhi = new Seguidor("Juhi");
        Seguidor urja = new Seguidor("Urja");
        Seguidor malay = new Seguidor("Malay");
        Seguidor ankit = new Seguidor("Ankit");
        Seguidor harsh = new Seguidor("Harsh");

        amirkhan.registrar(amar);
        amirkhan.registrar(juhi);
        amirkhan.registrar(urja);

        selenaGomez.registrar(malay);
        selenaGomez.registrar(ankit);
        selenaGomez.registrar(harsh);

        amirkhan.tuitar("Oi pessoal, encontrei este trailer interessante, confiram.");
        selenaGomez.tuitar("Bom dia..!!");

        amirkhan.desregistrar(juhi);

        amirkhan.tuitar("O teaser de Secret Superstar foi lançado..!!");

    }

}
