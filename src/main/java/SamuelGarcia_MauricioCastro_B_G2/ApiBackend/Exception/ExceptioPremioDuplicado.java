package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Exception;

@Getter
private String columnDuplicate;
public class ExceptioPremioDuplicado extends RuntimeException {
    public ExceptioPremioDuplicado(String message) {
        super(message);
    }

    public ExceptionColumnDuplicate(String message, String columnDuplicate) {
        super(message);
        this.columnDuplicate = columnDuplicate;
    }
}
