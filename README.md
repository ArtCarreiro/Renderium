# Renderium – Renderizador 3D em Java

Renderium é um renderizador 3D em Java usando Swing. Permite visualizar e rotacionar cubos 3D.

## Estrutura do Projeto

- `Application/RenderiumApplication.java` – Classe principal.
- `Models/StandardModels.java` – Classes `Vertex` e `Triangle`.
- `Models/Renderer.java` – Renderiza as faces do cubo 3D com Z-buffer.
- `Renderium/Matrix3.java` – Matriz 3x3 para rotação 3D.

## Funcionalidades

- Renderização de cubos 3D (até o momento)
- Rotação usando sliders:
    - Horizontal (heading)
    - Vertical (pitch)
- Z-buffer para sobreposição correta das faces.
