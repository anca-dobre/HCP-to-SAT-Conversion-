	
	Dobre Anca
	321CB

		Regulile transformarii si implementarea:


	1. 	In primul rand, pt a avea un Hamiltonian Cycle este nevoie ca fiecare nod al grafului sa aiba drum catre cel putin 2 noduri.
		Astfel, atunci cand citesc matricea in readMatrix, pe linia 0 si pe coloana 0 pastrez numarul de vecini ai fiecarui nod. Astfel, daca 
pe linia 0/ coloana 0 intalnesc un numar mai mic decat 2, inseamna ca am mai putini vecini decat trebuie, deci setez variabila ok pe 0, afisez eroare si
ies din functie

	2. 	Pentru a fi Hamiltonian Cycle trebuie ca in fiecare nod sa intre un drum si din fiecare nod sa plece un drum.
		Astfel, apelez functia prindCombinations care, pentru fiecare nod alege 2 drumuri (catre nodurile vecine) care trebuie sa existe, iar pe 
celelalte le neaga.
		Pentru fiecare nod pastrez in variabila posibilies numarul de posibilitati in care pot alege 2 drumuri si tin minte in displays cate am 
afisat deja.
		Parcurg nodurile cu indicicele mai mare, verific daca este vecin si ii setez pe 1. Apoi, drumurile ramase setate pe 0 

	3.	Trebuie sa verific ca graful ales sa fie tot unul neorientat, ceea ce presupune ca daca muchia de forma xij este setata pe 0 si muchia
xji trebuie sa fie setata tot pe 0 si invers.
		Pentru aceasta, in functia printOrientedGraph, pentru oricare 2 noduri i si j scriu conditia ca drumurile xij si xij sa fie simultan 0 sau 1.

	4.	Trebuie sa mai verific daca din fiecare nod se poate ajunge la nodul 1. De asemenea, trebuie ca, pentru fiecare nod i sa existe un singur 
ak-i setat pe 1.
		Astfel, parcurg toate nodurile.
		Verific in functia printRootNeighbours daca exista drum de la nodul i la 1. Daca nu exista inseamna ca a1-i trebuie sa fie setat pe 0, iar daca exista, trebuie ca 
drumul (x1-i sa fie 0 sau a1-i sa fie 1) si invers. Asta inseamna ca, daca s a ales drumul x1-i, a1-i nu poate fi 1 si daca drumul x1-i nu exista,
trebuie ca a1-i sa fie 1
		In functia printDistance ma asigur ca pentru nodul i, daca ak-i = 1, k este unic.

	5. 	Daca se poate ajunge din j in i pasi la 1, adica daca aij =1, inseamna ca trebuie sa existe un vecin de-al lui j, k, din care se poate ajunge la 1 in 
i-1 pasi si ca drumul de la k la j este setat pe 1. Totodata, trebuie sa ne asiguram ca nu se poate ajunge in mai putin de i pasi.
		Pentru  aceasta, in functia printVerify, vom lua fiecare drum aij si vom cauta un vecin care sa indeplineasca regula gasita si, de asemenea, ne vom asigura ca
toate  am-j, unde m = 1->i-1, sunt setate pe 0.
		

	Despre Complexitate:
	Complexitatea programului se gaseste adunand complexitatile functiilor. astfel:
		readMatrixFrom - O(n)
		printCombinations - O(n*n*n*n)
		printOrientedGraph - O(n*n)
		printDistance - O(n*n)
		printRootNeighbours - O(n)
		printVerify - O(n*n*n)
	Deci, complexitatea finala este O(n^4), deci transformarea este polinomiala
		
	
	