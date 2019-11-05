package br.com.automalabs.estacio.projetoAV1;

public class Cliente {
	
	private String nome;
	private String endereco;
	private String telefone;
	private String rg;
	private String cpf;
	private String dataNasc;
	private String carroAlugado;
	private String dataAluguel;
	private String dataDevolucao;

	/*
	 * Por que não usar um construtor mais elaborado, que receba os dados como parâmetros?
	 * Razão: Usar um contrutor mais elaborado requer que todos os dados do cliente sejam entregues de uma vez e validados também de uma vez.
	 * Isso tem o efeito de fazer com que o usuário só saiba de um erro após digitar e aplicar todos os dados
	 * Usar um contrutor simples que simplesmente inicializa todos os atributos e depois chamar os respectivos setters à medida
	 * que o usuário vai digitando os dados pareceu um modo mais adequado de lidar com o problema.    
	 */
	public Cliente() {
		this.nome="";
		this.endereco="";
		this.telefone="";
		this.rg="";
		this.cpf="";
		this.dataNasc="";
		this.carroAlugado="";
		this.dataAluguel="";
		this.dataDevolucao="";
	};
	


	//Como armazenamos a data de nascimento, precisamos de um método para calcular a idade 
	public int getIdade(){
		java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//dtf = dtf.withLocale( putAppropriateLocaleHere );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
		java.time.LocalDate birthDate = java.time.LocalDate.parse(this.dataNasc, dtf);
		java.time.LocalDate dataAtual = java.time.LocalDate.now();
        if ((birthDate != null) && (dataAtual != null)) {
            return java.time.Period.between(birthDate, dataAtual).getYears();
        } else {
            return 0;
        }
	};
	
	//usado para dataNasc, dataAluguel e dataDevolucao
	private boolean validarData(String data){
		java.text.DateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");

	        format.setLenient(false);

	        try {
	            format.parse(data);
	        } catch (java.text.ParseException e) {
	             return false;
	        }		
		return true;
		
	};
	

 private boolean validarTelefone(String telefone) {
	 //original para javascript:
	 //https://gist.github.com/jonathangoncalves/7bdec924e9bd2bdf353d6b7520820b62
	 
    //retira todos os caracteres não-numéricos (incluindo espaço,tab, etc)
    telefone = telefone.replaceAll("\\D","");
    
    //verifica se tem a qtde de numeros correta
    if (!(telefone.length() >= 10 && telefone.length() <= 11)) return false;

    //Se tiver 11 caracteres, verificar se começa com 9 o celular
    if (telefone.length() == 11 && Integer.parseInt(telefone.substring(2, 3)) != 9) return false;

    //verifica se não é nenhum numero digitado com todos os dígitos iguais
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(telefone.charAt(0)+"{"+telefone.length()+"}");
    java.util.regex.Matcher m = p.matcher(telefone);
    if(m.find()) return false;
    
    //DDDs validos
    Integer[] codigosDDD = {11, 12, 13, 14, 15, 16, 17, 18, 19,
        21, 22, 24, 27, 28, 31, 32, 33, 34,
        35, 37, 38, 41, 42, 43, 44, 45, 46,
        47, 48, 49, 51, 53, 54, 55, 61, 62,
        64, 63, 65, 66, 67, 68, 69, 71, 73,
        74, 75, 77, 79, 81, 82, 83, 84, 85,
        86, 87, 88, 89, 91, 92, 93, 94, 95,
        96, 97, 98, 99};
    //verifica se o DDD é valido (sim, da pra verificar rsrsrs)
    if ( java.util.Arrays.asList(codigosDDD).indexOf(Integer.parseInt(telefone.substring(0, 2))) == -1) return false;
    
    //Se o número só tiver dez digitos não é um celular e por isso o número logo após o DDD deve ser 2, 3, 4, 5 ou 7 
    Integer[] prefixos = {2, 3, 4, 5, 7};
    
    if (telefone.length() == 10 && java.util.Arrays.asList(prefixos).indexOf(Integer.parseInt(telefone.substring(2, 3))) == -1) return false;

    //se passar por todas as validações acima, então está tudo certo
    return true;
}
 
 //É possível unir este método com formatarCpf()
 private String formatarTelefone(String telefone) {
 	//Removo todos os caracteres não-numéricos
    telefone=telefone.replaceAll("\\D", "");
	try {
		//Neste ponto ou telefone ou tem 11 digitos (celulares) ou 10 digitos (fixos)
		String mascara= "(##) #####-####"; //Celulares
		if (telefone.length()==10) mascara="(##) ####-####"; //Telefones fixos
		javax.swing.text.MaskFormatter phoneFormatter = new javax.swing.text.MaskFormatter(mascara);
		javax.swing.JFormattedTextField txtPhone = new javax.swing.JFormattedTextField(phoneFormatter);
		txtPhone.setText(telefone);
		return txtPhone.getText();
	} catch (java.text.ParseException e) {
		e.printStackTrace();
		return "";
	}
 }
 
 //É possível unir este método com formatarTelefone()
 private String formatarCPF(String cpf) {
	 	//Removo todos os caracteres não-numéricos
	    cpf=cpf.replaceAll("\\D", "");
		try {
			javax.swing.text.MaskFormatter cpfFormatter = new javax.swing.text.MaskFormatter("###.###.###-##");
			javax.swing.JFormattedTextField txtCpf = new javax.swing.JFormattedTextField(cpfFormatter);
			txtCpf.setText(cpf);
			return txtCpf.getText();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return "";
		}
	 }

	public String getNome(){
		return this.nome;
	};
	
	public boolean setNome(String nome){
		
		if (nome.equals("")) {
			return false;
		}
		this.nome=nome;
		return true;	
		
	};	
	public String getEndereco(){
		return this.endereco;
	};
	
	public boolean setEndereco(String endereco){
		
		if (endereco.equals("")) {
			return false;
		}
		this.endereco=endereco;
		return true;	
	};
	
	public String getTelefone(){
		return this.telefone;
	};
	
	public boolean setTelefone(String telefone){
		//Estou considerando que neste sistema o telefone não seja obrigatório
	    if (telefone.contentEquals("")) return true;
	    
		if (validarTelefone(telefone)) {
			this.telefone=formatarTelefone(telefone);
			return true;
		}

		return false;		
		
		
	};	
	public String getRg(){
		return this.rg;
	};
	
	public boolean setRg(String rg){
		//Checar o RG adequadamente requer solicitar também o órgão expedidor
		//e conhecer as regras de validação desse órgão
		
		if (rg.equals("")) {
			return false;
		}
		this.rg=rg;
		return true;	
		
	};	

	
	
	public String getDataNasc(){
		return this.dataNasc;
	};
	
	public boolean setDataNasc(String dataNasc){
	
		if (validarData(dataNasc)) {
			this.dataNasc=dataNasc;
			return true;
		}

		return false;
		
	};
	
	public String getCarroAlugado() {
		return this.carroAlugado;
	}
	
	public void setCarroAlugado(String carroAlugado) {
		this.carroAlugado=carroAlugado;
	
	}
	
	public String getDataAluguel() {
		return this.dataAluguel;
	}
	
	public boolean setDataAluguel(String dataAluguel) {
		
		if (validarData(dataAluguel)) {
			this.dataAluguel=dataAluguel;
			return true;
		}

		return false;
	
	}

	public String getDataDevolucao() {
		return this.dataDevolucao;
	}
	
	public boolean setDataDevolucao(String dataDevolucao) {
		
		if (validarData(dataDevolucao)) {
			this.dataDevolucao=dataDevolucao;
			return true;
		}

		return false;	

	}	
	
	//Usado por isValidCPF().
	//Infelizmente usar "nested methods" em Java parece ser extraordinarimente complexo
	private int calcularDigito(String str) {
		int soma = 0;
		for(int indice = str.length()-1, digito; indice >= 0; indice--) {
			digito = Character.getNumericValue(str.charAt(indice));
			int valorPeso = str.length()+1 - indice;
			soma += digito*valorPeso;
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

   //usado por setCpf()	 
   private boolean isValidCPF(String cpf) {
	   cpf=cpf.replaceAll("\\D","");
	   if ((cpf==null) || (cpf.length()!=11) ||
            cpf.equals("00000000000") || cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999")) return false; 
	       Integer digito1 = calcularDigito(cpf.substring(0,9));
	       Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1);
	       return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	    }

	public boolean setCpf(String cpf){
		if(isValidCPF(cpf)) {
			this.cpf = formatarCPF(cpf);
			return true;
		} else {
			return false;
		}
	}	
	
	public String getCpf(){
		return this.cpf;
	};
}
