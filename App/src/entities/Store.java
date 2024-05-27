package entities;

import java.util.ArrayList;

public class Store {

    private Integer id;
    private String nome;
    private ArrayList<Customer> clientes = new ArrayList<Customer>();

      public Store() {}

      public Store(Integer id, String nome) {
          super();
          this.id = id;
          this.nome = nome;
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

      public ArrayList<Customer> getClientes() {
          return clientes;
      }

      public void setClientes(ArrayList<Customer> clientes) {
          this.clientes = clientes;
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
        Store other = (Store) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
      public String toString() {
          return "Store [id=" + id + ", nome=" + nome + ", clientes=" + clientes + "]";
      }
      
      

      

      // Métodos para adicionar, remover e buscar clientes
      // Outros métodos relacionados à gestão da loja
  }

