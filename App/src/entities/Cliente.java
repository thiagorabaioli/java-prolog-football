package entities;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private Integer id;
	private String nome;
    private String cidade;
    private String distrito;
    private Integer anosLealdade;
    private List<Cart> carts = new ArrayList<>();

    public Cliente() {}

	public Cliente(Integer id, String nome, String cidade, String distrito, Integer anosLealdade) {
		super();
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		this.distrito = distrito;
		this.anosLealdade = anosLealdade;
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public Integer getAnosLealdade() {
		return anosLealdade;
	}

	public void setAnosLealdade(Integer anosLealdade) {
		this.anosLealdade = anosLealdade;
	}



	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + "]";
	}



	
    
}
