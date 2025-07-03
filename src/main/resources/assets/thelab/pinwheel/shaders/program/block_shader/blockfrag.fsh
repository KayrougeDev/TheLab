uniform sampler2D Sampler0;
uniform sampler2D Sampler2;

in vec2 texCoord0;
in vec2 texCoord2;
in vec4 vertexColor;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord0);

    // Appliquer la lightmap (éclairage du monde)
    vec4 lightmapColor = texture(Sampler2, texCoord2);

    fragColor = color * lightmapColor * vertexColor;
}