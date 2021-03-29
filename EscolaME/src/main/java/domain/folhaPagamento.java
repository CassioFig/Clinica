package domain;

 @SuppressWarnings("FieldMayBeFinal")
public class folhaPagamento {
   
    private double salario;
    private double inss, irrf, salarioliquido;

    public folhaPagamento(double salario) {
        this.salario = salario;
    }
    
    public folhaPagamento(double salario, double inss, double irrf, double salarioliquido) {
        this.salario = salario;
        this.inss = inss;
        this.irrf = irrf;
        this.salarioliquido = salarioliquido;
    }

    public void calcularInss() {
        if(salario < 1751.82){
            this.inss = salario * 0.08;
        } else if(salario > 1751.81 && salario < 2919.73){
            this.inss = salario * 0.09;
        } else if(salario > 2919.72 && salario < 5839.46){
            this.inss = salario * 0.11;
        } else if (salario > 5839.45){
            this.inss = 817.66;
        }
    }

    public void calcularIrrf() {
        double salarioSInss = salario - this.getInss();
        if(salarioSInss < 1903.99){
            this.irrf = 0;
        } else if(salarioSInss > 1903.98 && salarioSInss < 2826.66){
            this.irrf = (salarioSInss * 0.075) - 142.80;
        } else if(salarioSInss > 2826.65 && salarioSInss < 3751.06) {
            this.irrf = (salarioSInss * 0.15) - 354.8;
        } else if(salarioSInss > 3751.05 && salarioSInss < 4664.69){
            this.irrf = (salarioSInss * 0.225) - 636.13;
        } else if(salarioSInss > 4664.68){
            this.irrf = (salarioSInss * 0.275) - 869.36;
        }
    }
    public void calculaSalarioliquido() {
        this.salarioliquido = this.salario - (this.getIrrf() + this.getInss());
    }

    public double getSalario() {
        return salario;
    }

    public double getInss() {
        return this.inss;
    }

    public double getIrrf() {
        return this.irrf;
    }

    public double getSalarioliquido() {
        return salarioliquido;
    }
    
    
    
}
