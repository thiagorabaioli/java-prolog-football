
% Clientes
cliente_store(1,'Alice', 'Aveiro', 3).
cliente_store(2,'Beatriz', 'Braga', 1).
cliente_store(3,'Carlos', 'Coimbra', 2).
cliente_store(4,'Diogo', 'Lisboa', 4).
cliente_store(5,'Eva', 'Porto', 1).
cliente_store(6,'Francisca', 'Faro', 3).
cliente_store(7,'Guilhermina', 'Viseu', 5).


player_position('Messi', 'Forward').
player_position('Ronaldo', 'Forward').
player_position('Neymar', 'Forward').
player_position('Pogba', 'Midfilder').
player_position('Kante', 'Midfilder').
player_position('Henderson', 'Midfilder').
player_position('Van Dijk', 'Defender').
player_position('Sergio Ramos', 'Defender').
player_position('Chiellini', 'Defender').



players_at_position(Position, Players) :-
findall(Player, player_position(Player,Position), Players).

 todos_clientes(Clientes) :-
    findall((Id, Name, Location, Years), cliente_store(Id, Name, Location, Years), Clientes).

% Predicado para buscar todos os jogadores
todos_jogadores(Jogadores) :-
    findall(Jogador, player_position(Jogador, _), Jogadores).

