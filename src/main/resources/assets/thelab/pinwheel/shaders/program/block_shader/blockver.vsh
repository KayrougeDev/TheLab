#include veil:light

layout(location = 0) in vec3 Position;
layout(location = 1) in vec4 Color;
layout(location = 2) in vec2 UV0;
layout(location = 3) in ivec2 UV2;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform sampler2D Sampler2;

out vec2 texCoord0;
out vec4 vertexColor;
out vec2 texCoord2;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    // Appliquer l'éclairage Minecraft
    //vertexColor = minecraft_mix_light(Light0_Direction, Light1_Direction, Normal, vec4(1.0));

    vertexColor = Color * minecraft_sample_lightmap(Sampler2, UV2);
    texCoord2 = UV2;
    texCoord0 = UV0;
}