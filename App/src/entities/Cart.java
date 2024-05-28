package entities;

import java.util.HashMap;
import java.util.Map;

public class Cart {
     private Integer id;
	  private Cliente cliente;
	  private Map<Item, Integer> items;

     
	  
	  public Cart() {
        this.items = new HashMap<>();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public Map<Item, Integer> getItems() {
        return items;
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

	public void setItems(Map<Item, Integer> items) {
		this.items = items;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Carrinho:\n");
        sb.append("Cliente: ").append(cliente != null ? cliente.toString() : "Nenhum").append("\n");
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            sb.append(entry.getKey()).append(", Quantidade: ").append(entry.getValue()).append("\n");
        }
        sb.append("Total: ").append(calcularTotal());
        return sb.toString();
    }
}


