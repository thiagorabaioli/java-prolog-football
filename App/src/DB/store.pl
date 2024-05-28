items_at_position(Position, Items) :-
findall(Item, item_position(Item,Position), Items).

% Predicado para buscar todos os clientes
 todos_clientes(Clientes) :-
    findall((Id, Name, Location, Years), cliente_store(Id, Name, Location, Years), Clientes).

% Predicado dinâmico para armazenar clientes
:- dynamic(cliente_store/4).

% Regra para adicionar um novo cliente ao banco de dados
adicionar_cliente(Id, Nome, Localizacao, Anos) :-
    assertz(cliente_store(Id, Nome, Localizacao, Anos)),
    salvar_cliente(Id, Nome, Localizacao, Anos).

% Regra para salvar um novo cliente no arquivo store.pl
salvar_cliente(Id, Nome, Localizacao, Anos) :-
    open('/home/ubuntu/LP/efolioB/java-prolog-football/App/src/DB/store.pl', append, Stream),
    write(Stream, 'cliente_store('),
    write(Stream, Id),
    write(Stream, ', \''),
    write(Stream, Nome),
    write(Stream, '\', \''),
    write(Stream, Localizacao),
    write(Stream, '\', '),
    write(Stream, Anos),
    write(Stream, ').\n'),
    close(Stream).





% Item em inventário IDitem Nome Categoria Custo Inventário
item_store(1,'Potion of Healing','potions',10.0,50).
item_store(2,'Wand of Fireball','wands',20.0,30).
item_store(3,'Enchanted Spellbook','enchanted_books',30.0,20).
item_store(4,'Crystal of Clairvoyance','crystals',15.0,40).
item_store(5,'Amulet of Protection','amulets',25.0,25).
item_store(6,'Standard Wand ','wands', 20.0,100).
item_store(7,'Love Potion','potions',10.0,50).
item_store(8,'Advanced Spellbook','enchanted Books',15,30).
item_store(9,'Crystal of Magic Vision','crystals',30.0,20).
item_store(10,'Flying Broomstick ','accessories',50.0,10).
item_store(11,' Enchanted Scroll','scrolls',8.0,50).
item_store(12,'Fairy Dust ','ingredients',5.0,100).


% Descontos por categoria de item
discount('potions', 0.03).
discount('wands', 0.2).
discount('enchanted_books', 0.3).
discount('crystals', 0.15).
discount('amulets', 0.25).
discount('accessories', 0).
discount('scrolls', 0.2).
discount('ingredients', 0.05).

% Desconto de lealdade por ano
loyalty_discount(1, 0.05).
loyalty_discount(2, 0.1).
loyalty_discount(3, 0.15).
loyalty_discount(4, 0.2).
loyalty_discount(5, 0.25).
loyalty_discount(6, 0.3).

% Custos de envio por distrito
shipping_cost('Aveiro', 5.0).
shipping_cost('Lisboa', 7.0).
shipping_cost('Porto', 10.0).
shipping_cost('Braga', 2.5).
shipping_cost('Coimbra', 5).
shipping_cost('Faro', 15).
shipping_cost('Viseu', 3).

% Clientes ID, Distrito, Anos de lealdade
cliente_store(1,'Alice','Aveiro',3).
cliente_store(2,'Beatriz','Braga',1).
cliente_store(3,'Carlos','Coimbra',2).
cliente_store(4,'Diogo','Lisboa',4).
cliente_store(5,'Eva','Porto',1).
cliente_store(6,'Francisca','Faro',3).
cliente_store(7,'Guilhermina','Viseu',5).


% histórico_de_compras(IDCliente,Data,ValorSemDescontos,DescontoCategoria,DescontoLealdade,CustoEnvio,Total)
histórico_de_compras(1,'20/05/2024',50,5,0,5,50).
histórico_de_compras(2,'21/05/2024',30,3,1,3,29).
histórico_de_compras(3,'22/05/2024',40,4,0,4,40).
histórico_de_compras(4,'23/05/2024',60,6,2.5,6,57.5).
histórico_de_compras(5,'23/05/2024',25,2.5,0,2.5,25).
histórico_de_compras(6,'25/05/2024',35,3.5,2,3.5,33).
histórico_de_compras(7,'26/05/2024',75,7.5,0,7.5,75).
histórico_de_compras(3,'27/05/2024',45,4.5,0,4.5,45).
histórico_de_compras(4,'28/05/2024',55,5.5,10,5,44.5).
histórico_de_compras(1,'28/05/2024',60,6,0,6,60).

cliente_store(25, 'Thiago', 'Loures', 5).
cliente_store(27, 'ThiagoRRRR', 'Loures', 7).
