package me.hyperboid.testmod;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ObamiumFoodComponents {
	public static final FoodComponent OBAMIUM_NUGGET = (
		new FoodComponent.Builder()
		.hunger(1)
		.saturationModifier(0.5f)
		.statusEffect(
			new StatusEffectInstance(
				StatusEffects.ABSORPTION, // effect
				300, // duration
				3 // intensity
			), 
			1.00f
		)
		.snack()
		.alwaysEdible()
		.build()
	);
}
