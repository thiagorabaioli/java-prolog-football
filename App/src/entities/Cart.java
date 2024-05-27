package entities;

import java.util.ArrayList;

public class Cart {
     private Integer id;
	  private Cliente cliente;
	  private ArrayList<Item> items = new ArrayList<Item>();

      public Cart() {}
	  
	  public Cart(Integer id, Cliente cliente) {
		super();
		this.id = id;
		this.cliente = cliente;
	  }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
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
        Cart other = (Cart) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
	public String toString() {
		return "Cart [id=" + id + ", cliente=" + cliente + ", items=" + items + "]";
	}

	
	  


	    // Métodos para adicionar, remover e buscar itens no carrinho
	    // Outros métodos relacionados ao carrinho de compras
	}

