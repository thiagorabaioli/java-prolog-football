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
    open('src/DB/store.pl', append, Stream),
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





% Item em inventário
item(1,'Potion of Healing','potions',10.0,50).
item(2,'Wand of Fireball','wands',20.0,30).
item(3,'Enchanted Spellbook','enchanted_books',30.0,20).
item(4,'Crystal of Clairvoyance','crystals',15.0,40).
item(5,'Amulet of Protection','amulets',25.0,25).
item(6,'Standard Wand ','wands', 20.0,100).
item(7,'Love Potion','potions',10.0,50).
item(8,'Advanced Spellbook','enchanted Books',15,30).
item(9,'Crystal of Magic Vision','crystals',30.0,20).
item(10,'Flying Broomstick ','accessories',50.0,10).
item(11,' Enchanted Scroll','scrolls',8.0,50).
item(12,'Fairy Dust ','ingredients',5.0,100).


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

% Clientes
cliente_store(1,'Alice','Aveiro',3).
cliente_store(2,'Beatriz','Braga',1).
cliente_store(3,'Carlos','Coimbra',2).
cliente_store(4,'Diogo','Lisboa',4).
cliente_store(5,'Eva','Porto',1).
cliente_store(6,'Francisca','Faro',3).
cliente_store(7,'Guilhermina','Viseu',5).



