Pruebas                             Peticion                    Resultado
1. Registro correcto                POST /api/auth/register     201. Usuario registrado exitosamente
2. Registro repetido                POST /api/auth/register     409. El correo ya esta en uso
3. Registro con usuario vacio       POST /api/auth/register     400. Bad resquest
4. Login correcto                   POST /api/auth/login        200. Inicio de sesion exitoso
5. Login con contraseña incorrecta  POST /api/auth/login        401. Credenciales invalidas
6. Login con ususario inexistente   POST /api/auth/login        401. Credenciales invalidas