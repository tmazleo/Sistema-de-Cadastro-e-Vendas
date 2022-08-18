package models;

public abstract class Usuario {
    private int id;
    private String nome;
    private TipoUsuario tipoUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String nome, TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        this.nome = nome;
    }
}
