
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import uyen.example.AccountService;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    private AccountService service;

    @BeforeEach
    void setUp() {
        service = new AccountService();
    }

    // Test đọc từ file CSV
    @ParameterizedTest(name = "Test {index}: username={0}, password={1}, email={2} → expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("Đăng ký tài khoản từ file dữ liệu")
    void testRegisterAccountWithCsv(String username, String password, String email, boolean expected) {
        boolean result = service.registerAccount(username, password, email);
        assertEquals(expected, result, "Kết quả không khớp với mong đợi");
    }

    @Test
    @DisplayName("Không đăng ký được nếu email trùng")
    void testDuplicateEmail() {
        assertTrue(service.registerAccount("user1", "password123", "abc@mail.com"));
        assertFalse(service.registerAccount("user2", "password123", "abc@mail.com"));
    }

    @Test
    @DisplayName("Email null hoặc sai định dạng sẽ bị từ chối")
    void testInvalidEmails() {
        assertFalse(service.isValidEmail(null));
        assertFalse(service.isValidEmail("abc.com"));
        assertFalse(service.isValidEmail("abc@"));
    }

    @Test
    @DisplayName("Password không hợp lệ khi < 7 kí tự hoặc null")
    void testInvalidPasswords() {
        assertFalse(service.isValidPassword("12345"));
        assertFalse(service.isValidPassword(null));
    }

    @Test
    @DisplayName("Username phải không được rỗng hoặc null")
    void testInvalidUsernames() {
        assertFalse(service.isUsernameValid(""));
        assertFalse(service.isUsernameValid("   "));
        assertFalse(service.isUsernameValid(null));
    }

    @Test
    @DisplayName("Hàm reset phải xóa danh sách email đã đăng ký")
    void testResetFunction() {
        service.registerAccount("user", "password123", "a@mail.com");
        assertFalse(service.isEmailUnique("a@mail.com"));
        service.reset();
        assertTrue(service.isEmailUnique("a@mail.com"));
    }
}
