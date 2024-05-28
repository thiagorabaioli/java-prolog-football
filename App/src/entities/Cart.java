package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {
     private Integer id;
	  private Cliente cliente;
	  private Map<Item, Integer> items;

     
	  
	  public Cart(Integer id, Cliente cliente) {
		super();
		this.id = id;
		this.cliente = cliente;
	  }

	  public Cart(){this.items = new HashMap<>();}

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
	

	public Map<Item, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Item, Integer> items) {
		this.items = items;
	}

	public void adicionarItem(Item item, int quantidade) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantidade);
        } else {
            items.put(item, quantidade);
        }
    }

    public void removerItem(Item item, int quantidade) {
        if (items.containsKey(item)) {
            int currentQuantity = items.get(item);
            if (currentQuantity <= quantidade) {
                items.remove(item);
            } else {
                items.put(item, currentQuantity - quantidade);
            }
        }
    }

    public double calcularTotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPreco() * entry.getValue())
                .sum();
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

